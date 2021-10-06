;;; Sierra Script 1.0 - (do not remove this comment)
(script# 81)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	Room81 0
)

(local
	printObj
)
(instance door of Actor
	(properties)
)

(instance guard of Actor
	(properties)
)

(instance guard2 of Actor
	(properties)
)

(instance guard3 of Actor
	(properties)
)

(instance guard4 of Actor
	(properties)
)

(instance Room81 of Room
	(properties
		picture 81
	)
	
	(method (init)
		(super init:)
		(door
			view: 135
			setLoop: 0
			setCel: 0
			posn: 266 116
			setPri: 4
			init:
		)
		(guard
			view: 135
			loop: 2
			cel: 0
			posn: 254 117
			setPri: 3
			init:
		)
		(if foundScumSoft
			(guard4
				view: 135
				setPri: 8
				setLoop: 2
				posn: 297 121
				init:
				stopUpd:
			)
			(guard
				view: 135
				setPri: 8
				setLoop: 2
				posn: 238 122
				stopUpd:
			)
			(door view: 777)
			(curRoom setScript: Actions)
			(Actions state: 7)
			(Actions seconds: 12)
		else
			(curRoom setScript: Actions)
		)
		(theMusic number: 86 loop: -1 play:)
	)
	
	(method (doit)
		(super doit:)
		(if (!= curRoomNum newRoomNum) (return))
		(if (and (== egoInvisible TRUE) (== beltState beltTURNEDON))
			(-- beltTimer)
		)
		(if
			(and
				(== egoInvisible TRUE)
				(== beltState beltTURNEDON)
				(<= beltTimer 0)
			)
			(curRoom drawPic: 81 DISSOLVE)
			(Print 81 0)
			(= egoInvisible FALSE)
			(= beltState beltDEPLETED)
		)
	)
	
	(method (handleEvent event)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look>')
						(cond 
							(
								(or
									(Said '/area')
									(Said '/around')
									(Said '[<around][/!*]')
								)
								(Print 81 1)
							)
							((Said '/guard,man,flunky') (Print 81 2))
							((Said '/sign,soft,dog,scumsoft') (Print 81 3))
							((Said '/door,entrance') (if (< (door y?) 142) (Print 81 4) else (Print 81 5)))
						)
					)
					(
						(or
							(Said 'turn<on/belt')
							(Said 'switch<on/belt')
							(Said 'activate/belt')
							(Said 'use/belt')
							(Said 'press/button')
						)
						(cond 
							((not (ego has: iInvisibilityBelt)) (Print 81 6))
							((!= wearingBelt TRUE) (Print 81 7))
							((== beltState beltDEPLETED) (Print 81 8))
							((== beltState beltTURNEDON) (Print 81 9))
							(else
								(= egoInvisible TRUE)
								(= beltState 1)
								(curRoom drawPic: 82 DISSOLVE)
								(RedrawCast)
								(Print 81 10)
								(= beltTimer 350)
							)
						)
					)
					(
					(or (Said 'turn<off/belt') (Said 'deactivate/belt'))
						(cond 
							((not (ego has: iInvisibilityBelt)) (DontHave))
							((not wearingBelt) (Print 81 11))
							((!= egoInvisible TRUE) (Print 81 12))
							(else
								(Print 81 13)
								(= egoInvisible FALSE)
								(= beltState beltTURNEDOFF)
								(curRoom drawPic: 81 DISSOLVE)
							)
						)
					)
					(
						(or
							(Said 'enter/soft,scumsoft,dog,sign,building')
							(Said 'go<in/soft,scumsoft,dog,sign,building')
							(Said 'go<to/soft,scumsoft,dog,sign,building')
							(Said 'enter/door')
						)
						(curRoom newRoom: 85)
					)
					((Said 'disembark,disembark/')
						(switch
							(= printObj
								(Print 81 14 #button {Ship} 1 #button {ScumSoft} 2)
							)
							(1 (curRoom newRoom: 80))
							(2 (curRoom newRoom: 85))
						)
					)
				)
			else
				0
			)
		)
	)
	
	(method (newRoom newRoomNumber)
		(theMusic fade:)
		(super newRoom: newRoomNumber)
	)
)

(instance Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 1))
			(1
				(Print 81 15)
				(= foundScumSoft TRUE)
				(door
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo 266 142 self
				)
			)
			(2
				(guard
					setLoop: 1
					setCycle: Walk
					setStep: 2 2
					setPri: 8
					setMotion: MoveTo 238 122 self
				)
			)
			(3
				(guard setLoop: 2)
				(guard2
					view: 135
					setLoop: 4
					setPri: 3
					posn: 249 133
					setCycle: Walk
					ignoreActors:
					setMotion: MoveTo 269 117 self
					init:
				)
			)
			(4
				(guard3
					view: 135
					setLoop: 4
					setCycle: Walk
					setPri: 3
					posn: 249 133
					setStep: 2 2
					setMotion: MoveTo 269 117 self
					init:
				)
				(guard2 setPri: 8 setPri: 9 setMotion: MoveTo 323 150)
			)
			(5
				(guard4
					view: 135
					setLoop: 4
					setCycle: Walk
					setPri: 3
					posn: 249 133
					setStep: 2 2
					setMotion: MoveTo 269 117 self
					init:
				)
				(guard3 setPri: 8 setMotion: MoveTo 323 150)
			)
			(6
				(guard4 setPri: 8 setMotion: MoveTo 297 121 self)
			)
			(7
				(guard4 setLoop: 2)
				(RedrawCast)
				(Print 81 16)
				(= seconds 12)
			)
			(8
				(switch
					(= printObj
						(Print 81 17
							#button {Stay Here} 1
							#button {Return to Ship} 2
							#button {Enter ScumSoft} 3
						)
					)
					(1 (= seconds 20) (-- state))
					(2 (curRoom newRoom: 80))
					(3 (curRoom newRoom: 85))
					(else 
						(= seconds 20)
						(-- state)
					)
				)
			)
		)
	)
)
