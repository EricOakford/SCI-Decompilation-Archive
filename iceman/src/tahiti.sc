;;; Sierra Script 1.0 - (do not remove this comment)
(script# rgTahiti)
(include game.sh)
(use Main)
(use Intrface)
(use EgoDead)
(use Game)
(use System)

(public
	tahiti 0
	tookTooLongScript 1
	braxMadScript 2
)

(class tahiti of Region
	(properties
		flags $0000
		volley 0
		pointFlag 0
		whichSong 0
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'climb[/palm]')
				(DontNeedTo)
			)
			((Said 'look[<at]/shoe,sandal')
				(Print 300 0)
			)
			((Said 'look[<at]/feet')
				(Print 300 1)
			)
			((Said 'detach,(get<off)/shoe,sandal')
				(Print 300 2)
			)
			((Said '(adjust<on),wear/shirt')
				(cond 
					((OneOf curRoomNum 2 14)
						(event claimed: FALSE)
					)
					((& (tahiti flags?) fWearingShirt)
						(Print 300 3)
					)
					(else
						(NotNow)
					)
				)
			)
			((Said '(get<off),detach/shirt')
				(cond 
					((OneOf curRoomNum 2 14)
						(event claimed: FALSE)
					)
					((& (tahiti flags?) fWearingShirt)
						(NotNow)
					)
					(else
						(Print 300 4)
					)
				)
			)
			((Said 'look[<in]/pocket')
				(cond 
					((== curRoomNum 6)
						(event claimed: FALSE)
					)
					((== (ego view?) 206)
						(Print 300 5)
					)
					(else
						(Print 300 6)
					)
				)
			)
			((Said 'look<down')
				(Print 300 7)
			)
			((and (Said '/earring,film,top>')
					(ego has: iEarring)
				)
				(if (OneOf curRoomNum 11 6)
					(Print 300 8)
					(event claimed: TRUE)
				else
					((ScriptID 317) handleEvent: event)
				)
			)
		)
	)
	
	(method (newRoom roomNum)
		(= keep
			(OneOf roomNum
				1 2 3 4 5 6 7 8 9
				10 11 12 13 14 16
				15 199 24 8 16
			)
		)
		(= initialized FALSE)
		(ego illegalBits: cWHITE)
		(super newRoom: &rest)
	)
)

(instance tookTooLongScript of Script
	(properties
		cycles 1200
	)
	
	(method (changeState newState)
		(if (= state newState)
			(EgoDead 907 3 0 300 9)
		)
	)
)

(instance braxMadScript of Script
	(properties
		cycles 2000
	)
	
	(method (changeState newState)
		(= state newState)
	)
)
