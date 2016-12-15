package com.sarality.task;

/**
 * Interface for the task to be run within an AsyncTask
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface Task<I, P, R> {

  R execute(I input, TaskProgressPublisher<P> progressPublisher);
}
