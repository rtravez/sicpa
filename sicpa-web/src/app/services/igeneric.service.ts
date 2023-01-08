import {Observable} from 'rxjs';

export interface IGenericService<T, ID> {
  /**
   * Save a given entity
   * @param t
   */
  save(t: T): Observable<T>;

  /**
   * Update a given entity
   * @param id
   * @param t
   */
  update(id: ID, t: T): Observable<T>;

  /**
   * Find by id
   * @param id
   */
  findById(id: ID): Observable<T>;

  /**
   * Find all
   */
  findAll(): Observable<T[]>;

  /**
   * Find by pages
   * @param page
   * @param size
   */
  findByPages(page: string, size: string): Observable<T[]>

  /**
   * Delete by id
   * @param id
   */
  delete(id: ID): Observable<any>;
}
