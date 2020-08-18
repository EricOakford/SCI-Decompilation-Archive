;;; Sierra Script 1.0 - (do not remove this comment)
(script# PQROOM)
(include game.sh)
(use Main)
(use Game)
(use System)


(local
	local0
)
(class PQRoom of Room
	(properties
		planeLeft 0
		planeTop 0
		planeRight 320
		planeBottom 148
		infoRoomSignal 0
	)
	
	(method (init)
		(if
			(and
				(== planeBottom 148)
				(OneOf curRoomNum 8 10 14 16 18)
			)
			(= planeBottom 190)
		)
		(if
			(and
				(== style -1)
				(or
					(and
						(OneOf prevRoomNum 8 10 14 16 18)
						(not (OneOf curRoomNum 8 10 14 16 18))
					)
					(and
						(not (OneOf prevRoomNum 8 10 14 16 18))
						(OneOf curRoomNum 8 10 14 16 18)
					)
					(OneOf curRoomNum 8 10 14 16 18)
				)
			)
			(= style SHOW_FADE_IN)
		)
		(super init: &rest)
	)
	
	(method (newRoom n)
		(if
			(and
				(== exitStyle -1)
				(or
					(OneOf n 8 10 14 16 18)
					(and
						(not (OneOf n 8 10 14 16 18))
						(OneOf curRoomNum 8 10 14 16 18)
					)
				)
			)
			(= exitStyle SHOW_FADE_OUT)
		)
		(theGame handsOff:)
		(super newRoom: n &rest)
	)
	
	(method (drawPic pic)
		(super drawPic: pic &rest)
		(self overlay:)
	)
	
	(method (overlay)
		(thePlane
			setRect: planeLeft planeTop planeRight planeBottom
			priority: 4
		)
		(UpdatePlane thePlane)
		(theInterface showInterface:)
	)
	
	(method (displayTravel &tmp [temp0 15])
	)
	
	(method (travelState)
		(return TRUE)
	)
)
