;;; Sierra Script 1.0 - (do not remove this comment)
(script# STREET)
(include game.sh)
(use Main)
(use Sleep)
(use Motion)
(use Game)
(use System)

(public
	Street 0
)

(local
	timesPickedLock
	timesClimbedWall
)
(instance Street of Locale
	(properties)
	
	(method (init)
		(if
			(and
				(not Night)
				(or (!= (cSound number?) 93) (== (cSound state?) 0))
			)
			(cSound priority: 0 number: 93 loop: -1 play:)
		)
		(super init: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 3] n temp4 thisControl)
		(cond 
			((super handleEvent: event))
			((!= (event type?) saidEvent))
			(
				(or
					(Said 'nap')
					(Said 'go[<to]/nap')
					(Said 'get,get/nap')
				)
				(cond 
					((and (< 700 Clock) (< Clock 2550))
						(HighPrint STREET 0)
					)
					((!= (ego onControl: origin) cBLACK)
						(HighPrint STREET 1)
					)
					((== curRoomNum 334)
						(EgoSleeps 5 0)
					)
					(else
						(ego setScript: egoSleepsInStreet)
					)
				)
			)
			((Said 'look/door')
				(HighPrint STREET 2)
			)
			((Said '/window')
				(HighPrint STREET 3)
			)
			((Said 'open/window')
				(HighPrint STREET 4)
			)
			((Said 'unlock,lockpick/door,hasp')
				(= thisControl (ego onControl: origin))
				(cond 
					((not Night)
						(HighPrint STREET 5)
					)
					((not [egoStats PICK])
						(HighPrint STREET 6)
					)
					((not (CanPickLocks))
						(HighPrint STREET 7)
					)
					((== thisControl cYELLOW)
						(curRoom notify: 1)
					)
					((or (== thisControl cLRED) (== thisControl cLGREEN) (== thisControl cLMAGENTA))
						(if (<= (Random 3 10) (++ timesPickedLock))
							(EgoDead STREET 8
								#title { Nobody said this job was easy._}
								#icon vEgoDefeated 1 0
							)
						else
							(TrySkill PICK 0 0)
							(HighPrint STREET 9)
						)
					)
					(else
						(HighPrint STREET 10)
					)
				)
			)
			((Said 'climb')
				(cond 
					((not [egoStats CLIMB])
						(HighPrint STREET 11)
					)
					((and (not Night) (!= curRoomNum 333))
						(HighPrint STREET 12)
					)
					((<= (Random 3 10) (++ timesClimbedWall))
						(HighPrint STREET 13)
						(= timesClimbedWall 10)
					)
					((TrySkill CLIMB tryClimbIntoTown 0)
						(switch curRoomNum
							(300
								(= n (if (> (ego x?) 130) 65 else 73))
							)
							(310 (= n 73))
							(320 (= n 65))
							(333 (= n 53))
							(334 (= n 53))
							(else 
								(HighPrint STREET 14)
								(= n 0)
							)
						)
						(if n
							(HighPrint STREET 15)
							(curRoom newRoom: n)
						)
					)
					(else
						(HighPrint STREET 16)
					)
				)
			)
			((Said 'look>')
				(cond 
					((or (Said '<down') (Said '/ground'))
						(HighPrint STREET 17)
					)
					((or (Said '/sky') (Said '<up'))
						(if Night
							(HighPrint STREET 18)
						else
							(HighPrint STREET 19)
						)
					)
					((Said '/wall')
						(HighPrint STREET 20)
					)
					((Said '/roof')
						(HighPrint STREET 21)
					)
					((Said '/hill')
						(HighPrint STREET 22)
					)
				)
			)
		)
	)
)

(instance egoSleepsInStreet of Script
	(method (changeState newState &tmp oldRoom)
		(switch (= state newState)
			(0
				(HandsOff)
				(HighPrint STREET 23)
				(ego view: vEgoTired setLoop: 3 cel: 0 setCycle: EndLoop self)
			)
			(1
				(EgoSleeps 5 0)
				(= seconds 5)
			)
			(2
				(= oldRoom curRoomNum)
				(= curRoomNum 999)
				(curRoom newRoom: oldRoom)
			)
		)
	)
)
