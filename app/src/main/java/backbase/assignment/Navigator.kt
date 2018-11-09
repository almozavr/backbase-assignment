package backbase.assignment

import backbase.assignment.domain.LocationUseCase.Location

interface Navigator {
  fun showDetails(location: Location)
}
