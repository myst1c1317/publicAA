package com.aa.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.aa.mapper.TourMapper;
import com.aa.bean.Tour;
import com.aa.service.TourService;
@Service
public class TourServiceImpl extends ServiceImpl<TourMapper, Tour> implements TourService{

}
