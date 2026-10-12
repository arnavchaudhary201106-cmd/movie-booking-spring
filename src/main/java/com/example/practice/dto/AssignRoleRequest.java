package com.example.practice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

    public class AssignRoleRequest {

        private Long userId;
        private Long roleId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public Long getRoleId() {
            return roleId;
        }

        public void setRoleId(Long roleId) {
            this.roleId = roleId;
        }
    }

