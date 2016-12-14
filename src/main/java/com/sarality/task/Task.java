package com.sarality.task;

import java.util.List;

/**
 * Interface for the task to be run within an AsyncTask
 *
 * @author abhideep@ (Abhideep Singh)
 */
public interface Task<I, P, R> {

  R execute(List<I> inputList, TaskProgressPublisher<P> progressPublisher);
}
