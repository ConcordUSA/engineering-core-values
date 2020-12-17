package com.example.slides.withoutLSP;

public class Square extends Rectangle {
    @Override
    public void setWidth(int width){
        m_width = width;
        m_height = width;
    }

    @Override
    public void setHeight(int height){
        m_width = height;
        m_height = height;
    }
}


