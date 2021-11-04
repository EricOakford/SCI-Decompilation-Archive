;;; Sierra Script 1.0 - (do not remove this comment)
(script# SQROOM)
(include game.sh)
(use Main)
(use Plane)
(use Game)


(class SQRoom of Room
	(method (newRoom n)
		(regions
			delete: self
			eachElementDo: #newRoom n
			addToFront: self
		)
		(if (== n 460)
			(= style SHOW_PLAIN)
		)
		(= newRoomNum n)
		(super newRoom: n)
	)
	
	(method (drawPic pic theStyle setProps)
		(if (not plane)
			((= plane (Plane new:)) init:)
		)
		(if (and (> argc 2) setProps)
			(if (!= pic -1)
				(= picture (= curPic pic))
			)
			(if (!= theStyle -1)
				(= style theStyle)
			)
		)
		(plane
			drawPic: pic
				(cond 
					((== style -1)
						SHOW_PALMORPH
					)
					((> argc 1)
						theStyle
					)
					(else
						style
					)
				)
		)
	)
)
