;;; Sierra Script 1.0 - (do not remove this comment)
(script# 206)
(include sci.sh)
(use Main)
(use Intrface)
(use Game)

(public
	houseOReg 0
)

(instance houseOReg of Rgn
	(properties)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(== currentAct 0)
				(not (Btst 39))
				(< curRoomNum 18)
				(& (ego onControl: 1) $0001)
			)
			(Print 206 0)
			(Bset 39)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return 1))
		(return
			(if (== (event type?) evSAID)
				(cond 
					(
						(and
							(== curRoomNum 10)
							(Said 'examine[<in]/read,colonel[/read]')
						)
						(if (& (ego onControl: 0) $0040)
							(Print 206 1)
						else
							(NotClose)
						)
					)
					(
					(and (== curRoomNum 12) (Said 'examine[<in]/kitchen'))
						(if (& (ego onControl: 0) $0040)
							(Print 206 1)
						else
							(NotClose)
						)
					)
					(
						(and
							(== curRoomNum 15)
							(Said 'examine[<in]/room<billiard')
						)
						(if (& (ego onControl: 0) $0040)
							(Print 206 1)
						else
							(NotClose)
						)
					)
					(
						(and
							(== curRoomNum 16)
							(or
								(Said 'examine[<in]/(hall,hall)[<entry]')
								(Said 'examine[<in]/room<billiard')
								(Said 'examine[<in]/parlor')
							)
						)
						(if (& (ego onControl: 0) $0040)
							(Print 206 1)
						else
							(NotClose)
						)
					)
					(
					(and (== curRoomNum 17) (Said 'examine[<in]/parlor'))
						(if (& (ego onControl: 0) $0040)
							(Print 206 1)
						else
							(NotClose)
						)
					)
					((Said 'examine>')
						(cond 
							((Said '<(in,through)/window,cabin')
								(if (< curRoomNum 20)
									(if (& (ego onControl: 0) $0040)
										(Print 206 1)
									else
										(NotClose)
									)
								else
									(NotClose)
								)
							)
							((Said '/cabin,mansion') (Print 206 2))
							((Said '/window') (Print 206 3))
							((Said '/door') (if (< curRoomNum 20) (Print 206 4) else (Print 206 5)))
							((Said '/column') (Print 206 6))
							((Said '/gallery') (Print 206 7))
						)
					)
					((Said 'open/window') (if (< curRoomNum 20) (Print 206 8) else (NotClose)))
					((Said 'open/door') (if (< curRoomNum 20) (Print 206 9) else (NotClose)))
					((Said 'break/window') (if (< curRoomNum 20) (Print 206 10) else (NotClose)))
					((Said 'climb/column') (if (< curRoomNum 20) (Print 206 11) else (NotClose)))
					((Said 'bang[/door]') (if (< curRoomNum 20) (Print 206 12) else (NotClose)))
					((Said 'rotate<on/ignite') (if (< curRoomNum 20) (Print 206 13) else (NotClose)))
				)
			else
				0
			)
		)
	)
)
