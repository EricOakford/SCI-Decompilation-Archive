;;; Sierra Script 1.0 - (do not remove this comment)
(script# 31)
(include game.sh)
(use Main)
(use Intrface)
(use Sound)
(use Motion)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Room31 0
)

(local
	ripple1
	local1
	local2
	local3
	islet
	fish3
	fish
	fishY
	whale
	shark
	fishX
	local11
	local12
	local13
)
(instance whaleMusic of Sound
	(properties
		number 36
	)
)

(instance sharkMusic of Sound
	(properties
		number 78
		loop -1
	)
)

(instance Room31 of Room
	(properties
		style BLACKOUT
	)
	
	(method (init)
		(= horizon 75)
		(= isIndoors FALSE)
		(= local12 4)
		(if howFast
			(= ripple1 (Prop new:))
			(ripple1
				view: 650
				loop: 1
				cel: 1
				posn: 155 57
				setPri: 0
				ignoreActors:
				setCycle: Forward
				init:
				hide:
			)
		)
		(self setRegions: OCEAN GULL)
		(Load VIEW 8)
		(Load VIEW 10)
		(Load VIEW 300)
		(Load VIEW 84)
		(Load VIEW 887)
		(Load SOUND 78)
		(if (<= (ego y?) (+ oldHorizon 2))
			(ego y: (- horizon 1))
		)
		(= oldHorizon horizon)
		(switch prevRoomNum
			(43
				(if (== (ego view?) 312)
					(if isNightTime
						(curRoom drawPic: 131)
					else
						(curRoom drawPic: 31)
					)
					(= local2 4)
					(= local1 2)
					(User canControl: FALSE)
					(= inCutscene TRUE)
					(ego posn: 5 100 setMotion: MoveTo 400 113)
				else
					(= local2 100)
					(= local1 100)
				)
			)
			(44
				(= local2 99)
				(= local1 100)
				((= islet (View new:))
					view: 526
					loop: 0
					cel: 0
					posn: 134 54
					init:
				)
				(if (IsObject ripple1) (ripple1 hide:))
				(if isNightTime
					(curRoom drawPic: 131)
				else
					(curRoom drawPic: 31)
				)
				(ego setScript: whaleActions)
				(whaleActions changeState: 10)
			)
			(1 (= local2 4) (= local1 0))
			(95 (= local2 3) (= local1 1))
			(13 (= local2 2) (= local1 0))
			(19 (= local2 1) (= local1 0))
			(25 (= local2 0) (= local1 0))
			(32
				(cond 
					((<= (ego x?) 0) (= local2 1) (= local1 5))
					((>= (ego y?) 189) (= local2 1) (= local1 3))
					((>= (ego x?) 319) (= local2 1) (= local1 3))
					(else (= local2 1) (= local1 3))
				)
			)
			(39 (= local2 2) (= local1 5))
			(41 (= local1 3) (= local2 2))
			(36 (= local2 3) (= local1 5))
			(38 (= local2 3) (= local1 3))
			(33 (= local2 4) (= local1 5))
			(34 (= local2 4) (= local1 4))
			(35 (= local2 4) (= local1 3))
		)
		(= local3 0)
		(= local11 0)
		(if (!= (ego view?) 312)
			(if (!= (ego view?) 84)
				(ego setCycle: Forward)
				(ego view: 8 init: hide: xStep: 2 yStep: 1)
			)
		else
			(ego setCycle: Forward)
			(ego init: xStep: 4 yStep: 2)
		)
		(if
			(and
				(== gamePhase getTheUnicorn)
				(< (Random 1 100) 40)
				(!= currentStatus egoRidingDolphin)
				(== swallowedByWhale FALSE)
				(< local1 30)
				(!= (ego script?) sharkActions)
			)
			(ego setScript: whaleActions)
		)
		(ego edgeHit: 0)
		(super init:)
		(self doit:)
		(= local13 local2)
	)
	
	(method (doit)
		(super doit:)
		(if
			(and
				(not isNightTime)
				(== (curRoom script?) 0)
				(== (Random 1 100) 9)
			)
			(curRoom setScript: fish3Actions)
		)
		(if
			(and
				(== (curRoom script?) 0)
				(not isNightTime)
				(== (Random 1 100) 10)
			)
			(curRoom setScript: fishActions)
		)
		(if (ego edgeHit?)
			(++ local3)
			(= global136 local1)
			(= global137 local2)
			(if (!= (ego view?) 312)
				(if (and (== local3 1) isNightTime)
					(= local1 1000)
					(= local2 1000)
				)
				(if (== local3 4) (Print 31 17))
				(if
					(and
						(== local3 local12)
						(!= (ego script?) sharkActions)
					)
					(ego setScript: drown)
					(drown changeState: 1)
					1
				)
			)
			(cond 
				((== (ego edgeHit?) 1)
					(if (and (< (++ local2) 10) (> local2 4))
						(= local2 0)
					)
					(ego posn: (ego x?) 188)
				)
				((== (ego edgeHit?) 3)
					(if (< (-- local2) 0) (= local2 4))
					(ego posn: (ego x?) (+ horizon (ego yStep?) 2))
				)
				((== (ego edgeHit?) 2) (-- local1) (ego posn: 1 (ego y?)))
				((== (ego edgeHit?) 4) (++ local1) (ego posn: 318 (ego y?)))
			)
			(cond 
				((and (== local1 100) (== local2 100)) (curRoom newRoom: 43))
				((and (== local1 1) (== local2 3)) (curRoom newRoom: 95))
				((== local1 0)
					(switch local2
						(4 (curRoom newRoom: 1))
						(2 (curRoom newRoom: 13))
						(1 (curRoom newRoom: 19))
						(0 (curRoom newRoom: 25))
					)
				)
				(
					(and
						(== local2 1)
						(or (== local1 3) (== local1 4) (== local1 5))
					)
					(curRoom newRoom: 32)
				)
				((and (== local2 2) (== local1 3)) (curRoom newRoom: 41))
				((and (== local2 2) (== local1 5)) (curRoom newRoom: 39))
				((and (== local2 3) (== local1 3))
					(if (== local13 4)
						(ego y: 100)
						(curRoom newRoom: 41)
					else
						(ego y: 170)
						(curRoom newRoom: 35)
					)
				)
				((and (== local2 3) (== local1 5))
					(if (<= (ego y?) 120)
						(curRoom newRoom: 33)
					else
						(curRoom newRoom: 39)
					)
				)
				((and (== local2 4) (== local1 3)) (if (!= (ego view?) 312) (curRoom newRoom: 35)))
				((and (== local2 4) (== local1 4)) (if (!= (ego view?) 312) (curRoom newRoom: 34)))
				(
				(and (== local2 4) (== local1 5) (!= (ego view?) 312)) (curRoom newRoom: 33))
			)
			(cond 
				((== (ego script?) sharkActions)
					(if (cast contains: shark)
						(cond 
							((< (ego x?) 10) (shark posn: (- (ego x?) 30) (ego y?)))
							((> (ego x?) 300) (shark posn: (+ (ego x?) 30) (ego y?)))
							((< (ego y?) (+ horizon 10)) (shark posn: (+ (ego x?) 20) (ego y?)))
							((> (ego y?) 174) (shark posn: (ego x?) (+ (ego y?) 30)))
							(else (shark posn: (- (ego x?) 20) (- (ego y?) 20)))
						)
					)
				)
				(
					(and
						(or
							(and (== local2 0) (> local1 2) (<= local1 5))
							(and
								(== local2 0)
								(<= local1 2)
								(> (Random 1 100) 50)
							)
							(and (!= isNightTime 0) (<= (Random 100) 40))
							(<= (Random 1 100) 20)
						)
						(!= (ego view?) 312)
						(!= (ego script?) sharkActions)
						(!= (ego script?) whaleActions)
					)
					(ego setScript: sharkActions)
				)
			)
			(if (== newRoomNum 31)
				(if (cast contains: fish3)
					(fish3 dispose:)
					(curRoom setScript: 0)
				)
				(if (cast contains: fish)
					(fish dispose:)
					(curRoom setScript: 0)
				)
				(if
					(and
						(not (if (== local2 99) (== local1 100)))
						(cast contains: islet)
					)
					(islet dispose:)
					(if (IsObject ripple1) (ripple1 show:))
				)
				(if isNightTime
					(addToPics dispose:)
					(curRoom drawPic: 131 9)
					(if (cast contains: whale)
						(ego setScript: 0)
						(whale dispose:)
						(whaleMusic dispose:)
					)
				else
					(addToPics dispose:)
					(curRoom drawPic: 31 9)
					(if (cast contains: whale)
						(ego setScript: 0)
						(whale dispose:)
						(whaleMusic dispose:)
					)
				)
				(cond 
					((and (== local1 100) (== local2 99))
						(if (not (cast contains: islet))
							((= islet (View new:))
								view: 526
								loop: 0
								cel: 0
								posn: 134 54
								init:
							)
							(if (IsObject ripple1) (ripple1 hide:))
						)
					)
					((IsObject ripple1) (ripple1 show:))
				)
				(if (not isNightTime)
					((View new:)
						view: 671
						cel: (Random 0 9)
						x: (Random 10 319)
						y: (Random 20 30)
						setPri: 0
						ignoreActors:
						init:
						addToPic:
					)
					((View new:)
						view: 671
						cel: (Random 0 9)
						x: (Random 10 319)
						y: (Random 20 30)
						setPri: 0
						ignoreActors:
						init:
						addToPic:
					)
					((View new:)
						view: 671
						cel: (Random 0 9)
						x: (Random 10 319)
						y: (Random 20 30)
						setPri: 0
						ignoreActors:
						init:
						addToPic:
					)
				)
				(if (!= (ego view?) 312) (ego setMotion: 0 show:))
			)
		)
	)
	
	(method (dispose)
		(ego setLoop: -1)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp inventorySaidMe)
		(if (event claimed?) (return TRUE))
		(return
			(if (== (event type?) saidEvent)
				(cond 
					((Said 'look[<around][/room,ocean,ocean,water]')
						(cond 
							((and (== local2 99) (== local1 100)) (Print 31 0))
							(isNightTime (Print 31 1))
							((== currentStatus 14) (Print 31 2))
							(else (Print 31 3))
						)
						(if (cast contains: shark) (Print 31 4))
					)
					((Said 'mount/dolphin')
						(if (== (ego view?) 312)
							(Print 31 5)
						else
							(Print 31 6)
						)
					)
					((Said '/shark')
						(if (cast contains: shark)
							(Print 31 7)
						else
							(Print 31 6)
						)
					)
					((Said 'look/island')
						(if (and (== local2 99) (== local1 100))
							(Print 31 8)
						else
							(Print 31 9)
						)
					)
					((== (ego view?) 312)
						(cond 
							((Said 'look/dolphin') (Print 31 10))
							((Said 'kiss/dolphin') (Print 31 11))
							((Said 'deliver>')
								(cond 
									((Said '/*/fish') (Print 31 12))
									((Said '/*/bird,gull') (Print 31 13))
									(
										(and
											(Said '/*/dolphin>')
											(= inventorySaidMe (inventory saidMe:))
										)
										(if (ego has: (inventory indexOf: inventorySaidMe))
											(Print 31 14)
										else
											(DontHave)
										)
									)
								)
							)
							((Said 'converse[/dolphin]') (Print 31 15))
							((Said 'pat/dolphin') (Print 31 16))
							(else (event claimed: FALSE))
						)
					)
				)
			else
				0
			)
		)
	)
)

(instance fish3Actions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= fish3 (Actor new:))
				(= fishX (Random 5 304))
				(= fishY (Random (+ (curRoom horizon?) 10) 179))
				(fish3
					view: 300
					loop: 0
					cel: 255
					posn: fishX fishY
					setCycle: EndLoop self
					init:
				)
				(if (< (fish3 distanceTo: ego) 30)
					(fish3 setCel: 0)
					(fish3Actions changeState: 1)
				)
			)
			(1
				(if (cast contains: fish3) (fish3 dispose:))
				(= seconds 3)
			)
			(2 (curRoom setScript: 0))
		)
	)
)

(instance fishActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= fish (Actor new:))
				(= fishX (Random 5 304))
				(= fishY (Random (+ (curRoom horizon?) 10) 179))
				(fish
					view: 301
					loop: 0
					cel: 255
					posn: fishX fishY
					setCycle: EndLoop self
					init:
				)
				(if (< (fish distanceTo: ego) 30)
					(fish setCel: 0)
					(fishActions changeState: 1)
				)
			)
			(1
				(if (cast contains: fish) (fish dispose:))
				(= seconds 3)
			)
			(2 (curRoom setScript: 0))
		)
	)
)

(instance drown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(1 (= seconds 10))
			(2
				(HandsOff)
				(ego
					view: 10
					setMotion: 0
					loop: 0
					cel: 255
					setCycle: EndLoop self
				)
			)
			(3
				(ego cel: 255 setCycle: EndLoop self)
			)
			(4
				(ego cel: 255 setCycle: EndLoop self)
			)
			(5
				(ego hide:)
				(if isNightTime
					(Print 31 18 #at -1 10)
				else
					(Print 31 19 #at -1 10)
				)
				(= dead TRUE)
			)
		)
	)
)

(instance whaleActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 2 8)))
			(1
				(= whale (Prop new:))
				(whaleMusic play:)
				(whale
					view: 315
					cel: 255
					loop: 0
					posn: 120 66
					init:
					setCycle: EndLoop self
				)
			)
			(2
				(if (cast contains: whale)
					(whale loop: 1 setCycle: Forward)
					(= seconds 3)
				)
			)
			(3
				(if (cast contains: whale)
					(whale loop: 2 cel: 255 setCycle: EndLoop self)
				)
			)
			(4
				(if (cast contains: whale) (whale dispose:))
				(= seconds 5)
			)
			(5
				(if (> (ego y?) 93)
					(HandsOff)
					(ego
						view: 84
						setMotion: 0
						cel: 255
						setLoop: 0
						setCycle: EndLoop self
					)
				else
					(self changeState: 12)
				)
			)
			(6
				(ego hide:)
				(HandsOn)
				(curRoom newRoom: 44)
			)
			(10
				(= seconds 0)
				(ego
					view: 84
					setLoop: 1
					cel: 255
					posn: 160 129
					setCycle: EndLoop self
					init:
				)
			)
			(11
				(Print 31 20)
				(ego view: 8 setCycle: Forward setLoop: -1)
				(HandsOn)
				(ego script: 0)
			)
		)
	)
)

(instance sharkActions of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds (Random 1 5)))
			(1
				(sharkMusic play:)
				((= shark (Actor new:))
					ignoreHorizon:
					posn: (Random 100 200) (Random 75 189)
					view: 887
					setCycle: Walk
					ignoreActors:
					xStep: 5
					yStep: 4
					setMotion: Chase ego 10 self
					init:
				)
			)
			(2
				(HandsOff)
				(ego
					setMotion: 0
					view: 10
					loop: 0
					cel: 6
					setCycle: EndLoop self
				)
			)
			(3
				(ego hide:)
				(sharkMusic dispose:)
				(if (cast contains: shark)
					(shark setMotion: MoveTo -20 100)
				)
				(= seconds 3)
			)
			(4
				(= isHandsOff FALSE)
				(Print 31 21 #at -1 10)
				(= dead TRUE)
			)
		)
	)
)
