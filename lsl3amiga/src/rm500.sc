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
	local88 = [-16564 5177 19666 27846 18175 19476 14668 -11668 -14778 -2049 -12039 -6221 -28275 -28200 -29441 -24077 -12441 8987 9137 6655]
	thePic =  500
	bambooRoom
	thirstTimer
	theEdge
)
(procedure (proc500_1 &tmp temp0)
	(curRoom drawPic: thePic)
	(if (proc500_2 (+ 0 bambooRoom))
		(curRoom overlay: (+ thePic 4))
	)
	(if (proc500_2 (+ 80 bambooRoom))
		(curRoom overlay: (+ thePic 2))
	)
	(if (proc500_2 (+ 240 bambooRoom))
		(curRoom overlay: (+ thePic 3))
	)
	(if (proc500_2 (+ 160 bambooRoom))
		(curRoom overlay: (+ thePic 1))
	)
)

(procedure (proc500_2 param1)
	(return
		(if (& [local88 (/ param1 16)] (>> $8000 (mod param1 16)))
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
	
	(method (init &tmp i)
		(for ((= i 500)) (< i 510) ((++ i))
			(Load PICTURE i)
		)
		(Load VIEW 800)
		(Load VIEW 501)
		(Load VIEW 502)
		(Load VIEW 503)
		(if (ego has: iWineBottle)
			(Load VIEW ((Inventory at: iWineBottle) view?))
		)
		(Load SOUND 501)
		(Load SOUND 502)
		(Load SOUND 503)
		(super init:)
		(music number: 500 loop: musicLoop play:)
		(if (not playingAsPatti)
			(= playingAsPatti TRUE)
			(= currentEgoView 800)
		)
		(self setScript: RoomScript)
		(if (== prevRoomNum 510)
			(ego posn: 177 26)
			(= bambooRoom 1)
		else
			(ego posn: (Random 130 234) 188)
			(= bambooRoom 68)
		)
		(proc500_1)
		(NormalEgo)
		(ego baseSetter: SteadyBase setCycle: SlowWalk init:)
	)
)

(instance RoomScript of Script
	(method (doit)
		(super doit:)
		(if (ego edgeHit?)
			(= theEdge (ego edgeHit?))
			(ego edgeHit: 0 illegalBits: 0)
			(theGame setCursor: waitCursor TRUE)
			(HandsOff)
			(++ thirstTimer)
			(self changeState: 0)
			(cond 
				((< thirstTimer 8)
					(ego view: 800 moveSpeed: 0)
				)
				((< thirstTimer 14)
					(ego view: 501 moveSpeed: 0)
					(if (!= 501 (music number?))
						(music fade:)
					)
				)
				((< thirstTimer 17)
					(ego view: 502 moveSpeed: 1)
					(if (!= 502 (music number?))
						(music fade:)
					)
				)
				((< thirstTimer 18)
					(ego view: 503 moveSpeed: 2)
					(if (!= 503 (music number?))
						(music fade:)
					)
				)
				(else
					(ego view: 503 moveSpeed: 3)
					(self changeState: 2)
				)
			)
			(switch theEdge
				(NORTH
					(if (== bambooRoom 1)
						(music fade:)
						(if (not (Btst fPassedMaze))
							(theGame changeScore: 100)
							(Print 500 0)
							(Print 500 1)
						)
						(curRoom newRoom: 510)
						(return)
					else
						(-= bambooRoom 8)
					)
				)
				(SOUTH
					(if (== bambooRoom 68)
						(curRoom newRoom: 245)
						(return)
					else
						(+= bambooRoom 8)
					)
				)
				(EAST
					(++ bambooRoom)
				)
				(WEST
					(-- bambooRoom)
				)
			)
			(if (== thePic 505)
				(= thePic 500)
				(switch theEdge
					(NORTH
						(ego posn: (Random 130 234) 187)
					)
					(SOUTH
						(ego posn: 177 26)
					)
					(EAST
						(ego posn: 1 74)
					)
					(else
						(ego posn: 317 74)
					)
				)
			else
				(= thePic 505)
				(switch theEdge
					(NORTH
						(ego posn: (Random 80 163) 187)
					)
					(SOUTH
						(ego posn: 188 26)
					)
					(EAST
						(ego posn: 1 83)
					)
					(else
						(ego posn: 314 128)
					)
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
			(0
				(= seconds 4)
			)
			(1
				(cond 
					(
						(and
							(>= thirstTimer 8)
							(<= thirstTimer 13)
							(!= 501 (music number?))
						)
						(music number: 501 loop: musicLoop play:)
					)
					(
						(and
							(>= thirstTimer 14)
							(<= thirstTimer 16)
							(!= 502 (music number?))
						)
						(music number: 502 loop: musicLoop play:)
					)
					(
						(and
							(<= thirstTimer 18)
							(>= thirstTimer 17)
							(!= 503 (music number?))
						)
						(music number: 503 loop: musicLoop play:)
					)
				)
				(cond 
					((== thirstTimer 4)
						(Print 500 13)
					)
					((== thirstTimer 8)
						(Print 500 14)
					)
					((== thirstTimer 12)
						(Print 500 15)
					)
					((== thirstTimer 16)
						(Print 500 16)
						(Print 500 17)
						(Print 500 18)
						(Print 500 19)
					)
				)
			)
			(2
				(= seconds 3)
			)
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
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return (event claimed?))
		)
		(return
			(cond 
				((Said 'get/bamboo')
					(Print 500 2)
				)
				((Said 'climb/bamboo')
					(Print 500 3)
				)
				((Said 'attack/bamboo')
					(Print 500 4)
				)
				((Said 'nightstand,(get,nightstand<up)')
					(Print 500 5)
				)
				(
					(or
						(Said 'sip/water')
						(Said 'get/drink<1')
						(Said 'use,drink,drain/water,beer,bottle')
					)
					(cond 
						((!= currentStatus egoNORMAL)
							(GoodIdea)
						)
						((not (ego has: iWineBottle))
							(DontHave)
						)
						((== ((Inventory at: iWineBottle) view?) 28)
							(Print 500 6 #icon 28 0 0)
						)
						(else
							(Ok)
							(theGame changeScore: 20)
							(= thirstTimer 0)
							(music number: 500 loop: musicLoop play:)
							(Print 500 7 #icon 29 0 0)
							(Print 500 8)
							(PutInRoom iWineBottle)
							(NormalEgo)
							(ego baseSetter: SteadyBase setCycle: SlowWalk)
							(self changeState: 0)
						)
					)
				)
				((Said 'look>')
					(cond 
						((Said '[/area]')
							(Print 500 9)
							(Print 500 10 #at -1 144)
						)
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
	(method (doit)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) 2))
		(ego brLeft: (- (ego x?) 10))
		(ego brRight: (+ (ego x?) 10))
	)
)

(instance SlowWalk of Forward	;was a class, but not in the table
	(properties
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
