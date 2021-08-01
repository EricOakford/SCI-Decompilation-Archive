;;; Sierra Script 1.0 - (do not remove this comment)
(script# 767)
(include game.sh)
(use Main)
(use Motion)
(use Actor)


(class Door of Prop
	(properties
		openDoorNumber 8122
		closeDoorNumber 8124
	)
	
	(method (setCycle cType)
		(if (and argc (IsObject cType))
			(theAudio
				number:
					(if (cType isKindOf: BegLoop)
						(self closeDoorNumber?)
					else
						(self openDoorNumber?)
					)
				loop: 1
				play:
			)
		)
		(super setCycle: cType &rest)
	)
)
