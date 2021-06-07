;;; Sierra Script 1.0 - (do not remove this comment)
(script# 500)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use System)

(public
	rm500 0
	proc500_1 1
	proc500_2 2
)

(local
	[msgBuf 66]
	[titleBuf 22]
	[local88 20] = [-16564 5177 19666 27846 18175 19476 14668 -11668 -14778 -2049 -12039 -6221 -28275 -28200 -29441 -24077 -12441 8987 9137 6655]
	local108 =  500
	local109
	thirstTimer
	gEgoEdgeHit
)
(procedure (proc500_1 &tmp temp0)
	(curRoom drawPic: local108)
	(if (proc500_2 (+ 0 local109))
		(curRoom overlay: (+ local108 4))
	)
	(if (proc500_2 (+ 80 local109))
		(curRoom overlay: (+ local108 2))
	)
	(if (proc500_2 (+ 240 local109))
		(curRoom overlay: (+ local108 3))
	)
	(if (proc500_2 (+ 160 local109))
		(curRoom overlay: (+ local108 1))
	)
)

(procedure (proc500_2 param1)
	(return
		(if
		(& [local88 (/ param1 16)] (>> $8000 (mod param1 16)))
			1
		else
			0
		)
	)
)

(instance rm500 of Room
	(properties
		picture 500
		horizon 22
	)
	
	(method (init &tmp temp0)
		(= temp0 500)
		(while (< temp0 510)
			(Load PICTURE temp0)
			(++ temp0)
		)
		(Load VIEW 800)
		(Load VIEW 501)
		(Load VIEW 502)
		(Load VIEW 503)
		(if (ego has: iBottleOfWine) (Load VIEW ((Inventory at: iBottleOfWine) view?)))
		(Load SOUND 501)
		(Load SOUND 502)
		(Load SOUND 503)
		(super init:)
		(music number: 500 loop: global108 play:)
		(if (not playingAsPatti)
			(= playingAsPatti TRUE)
			(= currentEgoView 800)
		)
		(self setScript: RoomScript)
		(if (== prevRoomNum 510)
			(ego posn: 177 26)
			(= local109 1)
		else
			(ego posn: (Random 130 234) 188)
			(= local109 68)
		)
		(proc500_1)
		(NormalEgo)
		(ego baseSetter: SteadyBase setCycle: SlowWalk init:)
	)
)

(instance RoomScript of Script
	(properties)
	
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(= gEgoEdgeHit (ego edgeHit?))
			(ego edgeHit: 0 illegalBits: 0)
			(theGame setCursor: waitCursor TRUE)
			(HandsOff)
			(++ thirstTimer)
			(self changeState: 0)
			(cond 
				((< thirstTimer 8) (ego view: 800 moveSpeed: 0))
				((< thirstTimer 14)
					(ego view: 501 moveSpeed: 0)
					(if (!= 501 (music number?)) (music fade:))
				)
				((< thirstTimer 17)
					(ego view: 502 moveSpeed: 1)
					(if (!= 502 (music number?)) (music fade:))
				)
				((< thirstTimer 18)
					(ego view: 503 moveSpeed: 2)
					(if (!= 503 (music number?)) (music fade:))
				)
				(else (ego view: 503 moveSpeed: 3) (self changeState: 2))
			)
			(switch gEgoEdgeHit
				(1
					(if (== local109 1)
						(music fade:)
						(if (not (Btst fPassedMaze))
							(theGame changeScore: 100)
							(Print 500 0)
							(Print 500 1)
						)
						(curRoom newRoom: 510)
						(return)
					else
						(= local109 (- local109 8))
					)
				)
				(3
					(if (== local109 68)
						(curRoom newRoom: 245)
						(return)
					else
						(= local109 (+ local109 8))
					)
				)
				(2 (++ local109))
				(4 (-- local109))
			)
			(if (== local108 505)
				(= local108 500)
				(switch gEgoEdgeHit
					(1
						(ego posn: (Random 130 234) 187)
					)
					(3 (ego posn: 177 26))
					(2 (ego posn: 1 74))
					(else  (ego posn: 317 74))
				)
			else
				(= local108 505)
				(switch gEgoEdgeHit
					(1
						(ego posn: (Random 80 163) 187)
					)
					(3 (ego posn: 188 26))
					(2 (ego posn: 1 83))
					(else  (ego posn: 314 128))
				)
			)
			(proc500_1)
			(Animate (cast elements?) FALSE)
			(ego illegalBits: cWHITE)
			(HandsOn)
			(theGame setCursor: normalCursor (HaveMouse))
			(return)
		)
		(if (== (GameIsRestarting) 2)
			(proc500_1)
			(Animate (cast elements?) FALSE)
		)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 4))
			(1
				(cond 
					(
						(and
							(>= thirstTimer 8)
							(<= thirstTimer 13)
							(!= 501 (music number?))
						)
						(music number: 501 loop: global108 play:)
					)
					(
						(and
							(>= thirstTimer 14)
							(<= thirstTimer 16)
							(!= 502 (music number?))
						)
						(music number: 502 loop: global108 play:)
					)
					(
						(and
							(<= thirstTimer 18)
							(>= thirstTimer 17)
							(!= 503 (music number?))
						)
						(music number: 503 loop: global108 play:)
					)
				)
				(cond 
					((== thirstTimer 4) (Print 500 13))
					((== thirstTimer 8) (Print 500 14))
					((== thirstTimer 12) (Print 500 15))
					((== thirstTimer 16)
						(Print 500 16)
						(Print 500 17)
						(Print 500 18)
						(Print 500 19)
					)
				)
			)
			(2 (= seconds 3))
			(3
				(Print 500 20)
				(= seconds 3)
			)
			(4
				(Print 500 21)
				(= seconds 3)
			)
			(5
				(HandsOff)
				(Print 500 22)
				(ego
					illegalBits: 0
					setMotion: 0
					view: 504
					cel: 0
					cycleSpeed: 2
					setCycle: EndLoop self
				)
			)
			(6
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 505
					register: (Format @msgBuf 500 23)
					next: (Format @titleBuf 500 24)
				)
			)
		)
	)
	
	(method (handleEvent event &tmp temp0)
		(if
		(or (!= (event type?) saidEvent) (event claimed?))
			(return (event claimed?))
		)
		(return
			(cond 
				((Said 'get/bamboo') (Print 500 2))
				((Said 'climb/bamboo') (Print 500 3))
				((Said 'attack/bamboo') (Print 500 4))
				((Said 'nightstand,(get,nightstand<up)') (Print 500 5))
				(
					(or
						(Said 'sip/water')
						(Said 'get/drink<1')
						(Said 'use,drink,drain/water,beer,bottle')
					)
					(cond 
						((!= currentStatus 0) (GoodIdea))
						((not (ego has: iBottleOfWine)) (DontHave))
						((== ((Inventory at: iBottleOfWine) view?) 28) (Print 500 6 #icon 28 0 0))
						(else
							(Ok)
							(theGame changeScore: 20)
							(= thirstTimer 0)
							(music number: 500 loop: global108 play:)
							(Print 500 7 #icon 29 0 0)
							(Print 500 8)
							(PutInRoom iBottleOfWine)
							(NormalEgo)
							(ego baseSetter: SteadyBase setCycle: SlowWalk)
							(self changeState: 0)
						)
					)
				)
				((Said 'look>')
					(cond 
						((Said '[/area]') (Print 500 9) (Print 500 10 #at -1 144))
						((Said '/bamboo')
							(Print 500 11)
							(Print (Format @msgBuf 500 12 bambooStalksSeen) #at -1 144)
							(++ bambooStalksSeen)
						)
					)
				)
			)
		)
	)
)

(instance SteadyBase of Code
	(properties)
	
	(method (doit)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) 2))
		(ego brLeft: (- (ego x?) 10))
		(ego brRight: (+ (ego x?) 10))
	)
)

(class SlowWalk of Forward
	(properties
		client 0
		caller 0
		cycleDir 1
		cycleCnt 0
		completed 0
	)
	
	(method (doit)
		(if
			(or
				(!= (client x?) (client xLast?))
				(!= (client y?) (client yLast?))
			)
			(super doit:)
		)
	)
)
