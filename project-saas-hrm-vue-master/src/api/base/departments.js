import {createAPI, createFormAPI} from '@/utils/request'
import data from "../../icons/generateIconsView";

export const list = data => createAPI('/company/departments', 'get', data)
export const save = data => createAPI('/company/departments', 'post', data)
export const update = data => createAPI('/company/departments', 'put', data)
export const detail = data => createAPI(`/company/departments/${data.id}`, 'get', data)
export const remove = data => createAPI(`/company/departments/${data.id}`, 'delete', data)

export const saveOrUpdate = data => {
  return data.id ? update(data) : save(data)
}
