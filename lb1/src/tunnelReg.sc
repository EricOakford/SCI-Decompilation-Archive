;;; Sierra Script 1.0 - (do not remove this comment)
(script# 242)
(include sci.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use System)

(public
	tunnelReg 0
)

(instance myMusic of Sound
	(properties)
)

(instance tunnelReg of Rgn
	(properties)
	
	(method (init)
		(super init:)
		(if (!= curRoomNum 52)
			(myMusic number: 61 loop: -1 play:)
		)
	)
	
	(method (dispose)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp newEvent temp1 temp2 castFirst theCastFirst temp5)
		(if (event claimed?) (return 1))
		(if
			(and
				(== (event type?) evMOUSEBUTTON)
				(not (& (event modifiers?) emSHIFT))
				(or
					(== curRoomNum 51)
					(== curRoomNum 55)
					(and (== curRoomNum 56) (& (ego onControl:) $0004))
				)
			)
			(event claimed: 1)
			(while (!= 2 ((= newEvent (Event new:)) type?))
				(ego setMotion: MoveTo (newEvent x?) (ego y?))
				(newEvent dispose:)
			)
			(newEvent dispose:)
		)
		(return
			(if (== (event type?) evSAID)
				(cond 
					((Said 'examine>')
						(= temp1 (= temp2 0))
						(= castFirst (cast first:))
						(while castFirst
							(= theCastFirst (cast next: castFirst))
							(if
								(and
									(IsObject (= temp5 (NodeValue castFirst)))
									(== (temp5 view?) 155)
									(not (& (temp5 signal?) $0080))
								)
								(if (== (temp5 loop?) 0) (= temp2 1) else (= temp1 1))
							)
							(= castFirst theCastFirst)
						)
						(cond 
							((Said '/beam')
								(if (or temp2 (== curRoomNum 51))
									(Print 242 0)
								else
									(DontSee)
								)
							)
							((or (Said '/dirt') (Said '<down[/!*]')) (Print 242 1))
							((Said '/wall') (Print 242 2))
							((Said '/boulder')
								(if (and (== curRoomNum 55) temp1)
									(Print 242 3)
								else
									(DontSee)
								)
							)
							((Said '/eye') (Print 242 4))
							((Said '/mouse') (DontSee))
						)
					)
					((Said 'hear') (Print 242 5))
					((Said 'extinguish,extinguish,(rotate<off)') (Print 242 6))
				)
			else
				0
			)
		)
	)
)
