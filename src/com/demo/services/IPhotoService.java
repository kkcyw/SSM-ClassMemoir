package com.demo.services;

import java.util.ArrayList;

import com.demo.entity.Photo;

public interface IPhotoService {
	public ArrayList<Photo> findAllPhoto();
	public void insertOnePhoto(String Pname,String remarks);
	public void deleteOnePhoto(int id);
	public ArrayList<Photo> findPhotoByremarks(String remarks);
	public Photo findPhotoByPid(int id);
}
