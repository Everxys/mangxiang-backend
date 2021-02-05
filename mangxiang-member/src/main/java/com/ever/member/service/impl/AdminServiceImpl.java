package com.ever.member.service.impl;

import com.ever.member.entity.Admin;
import com.ever.member.mapper.AdminMapper;
import com.ever.member.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Everxys
 * @since 2021-02-04
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {

}
