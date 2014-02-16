package com.github.codingricky.marvel;

import java.io.IOException;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.codingricky.marvel.model.Comic;
import com.github.codingricky.marvel.model.Creator;
import com.github.codingricky.marvel.model.Event;
import com.github.codingricky.marvel.model.MarvelCharacter;
import com.github.codingricky.marvel.model.Result;
import com.github.codingricky.marvel.model.Series;
import com.github.codingricky.marvel.model.Story;
import us.monoid.web.Resty;

public class RestClient {

    private final URLFactory urlFactory;

    public RestClient(String privateKey, String publicKey) {
        this.urlFactory = new URLFactory(privateKey, publicKey);
    }

    public Result<Comic> getCharactersComics(int characterId) throws IOException {
        final String result = getURL(urlFactory.getCharactersComicsURL(characterId));
        return convertToResult(Comic.class, result);
    }

    public Result<Event> getCharactersEvents(int characterId) throws IOException {
        final String result = getURL(urlFactory.getCharactersEventURL(characterId));
        return convertToResult(Event.class, result);
    }

    public Result<Story> getCharactersStories(int characterId) throws IOException {
        final String result = getURL(urlFactory.getCharactersStoriesURL(characterId));
        return convertToResult(Story.class, result);
    }

    public Result<Series> getCharactersSeries(int characterId) throws IOException {
        final String result = getURL(urlFactory.getCharactersSeriesURL(characterId));
        return convertToResult(Series.class, result);
    }

    public Result<MarvelCharacter> getCharacters(Parameters parameters) throws IOException {
        final String result = getURL(urlFactory.getCharactersURL(parameters));
        return convertToResult(MarvelCharacter.class, result);
    }

    public Result<MarvelCharacter> getCharacter(int characterId) throws IOException {
        final String result = getURL(urlFactory.getCharacterURL(characterId));
        return convertToResult(MarvelCharacter.class, result);
    }

    public Result<Comic> getComics() throws IOException {
        final String result = getURL(urlFactory.getComicsURL());
        return convertToResult(Comic.class, result);
    }

    public Result<Comic> getComics(int comicId) throws IOException {
        final String result = getURL(urlFactory.getComicsURL(comicId));
        return convertToResult(Comic.class, result);
    }

    public Result<Event> getComicsEvents(int comicId) throws IOException {
        final String result = getURL(urlFactory.getComicsEventsURL(comicId));
        return convertToResult(Event.class, result);
    }

    public Result<Story> getComicsStories(int comicId) throws IOException {
        final String result = getURL(urlFactory.getComicsStoriesURL(comicId));
        return convertToResult(Story.class, result);
    }

    public Result<Creator> getComicsCreators(int comicId) throws IOException {
        final String result = getURL(urlFactory.getComicsCharactersURL(comicId));
        return convertToResult(Creator.class, result);
    }

    public Result<MarvelCharacter> getComicsCharacters(int comicId) throws IOException {
        final String result = getURL(urlFactory.getComicsCharactersURL(comicId));
        return convertToResult(MarvelCharacter.class, result);
    }

    private <T> Result<T> convertToResult(Class clazz, String result) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Result.class, clazz);
        return objectMapper.readValue(result, javaType);
    }

    private String getURL(String url) throws IOException {
        return new Resty().text(url).toString();
    }

}
