;;; Sierra Script 1.0 - (do not remove this comment)
(script# 550)
(include game.sh)
(use Main)
(use Intrface)
(use Motion)
(use Game)
(use Invent)
(use User)
(use Actor)
(use System)

(public
	rm550 0
)

(local
	drownLoop
	[msgBuf 55]
	[titleBuf 22]
)
(instance rm550 of Room
	(properties
		picture 550
		horizon 50
		south 540
	)
	
	(method (init)
		(Load VIEW 551)
		(Load VIEW 552)
		(Load VIEW 553)
		(Load SOUND 6)
		(super init:)
		(aLog setCycle: Forward init:)
		(self setScript: RoomScript)
		(if debugging
			(= playingAsPatti TRUE)
			(= currentEgoView 803)
			((Inventory at: iDress) view: 31)
			(= currentStatus egoNORMAL)
		)
		(NormalEgo)
		(ego posn: 159 188 loop: 3 init: viewer: PattiViewer)
	)
)

(instance RoomScript of Script
	(method (changeState newState)
		(switch (= state newState)
			(0)
			(1
				(Ok)
				(theGame changeScore: 10)
				(HandsOff)
				(= currentStatus 551)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo (+ (aLog x?) 20) (+ (aLog y?) -14) self
				)
			)
			(2
				(aLog hide:)
				(ego
					cycleSpeed: 1
					moveSpeed: 1
					viewer: 0
					posn: (aLog x?) (aLog y?)
					view: 553
					setLoop: 0
					setCycle: Forward
					setMotion: MoveTo 170 131 self
				)
			)
			(3
				(aLog posn: 170 131 show:)
				(ego
					viewer: PattiViewer
					posn: (+ (aLog x?) 20) (+ (aLog y?) -14)
				)
				(NormalEgo loopS 551)
				(= currentStatus egoNORMAL)
			)
			(4
				(Ok)
				(HandsOff)
				(ego
					illegalBits: 0
					ignoreActors:
					setMotion: MoveTo (+ (aLog x?) 20) (+ (aLog y?) -14) self
				)
			)
			(5
				(aLog hide:)
				(ego
					viewer: 0
					cycleSpeed: 1
					posn: (aLog x?) (aLog y?)
					view: 553
					loop: 1
					cel: 0
					setCycle: EndLoop self
				)
			)
			(6
				(ego setLoop: 2 setCycle: Forward)
				(= currentStatus 550)
				(if (!= (aLog x?) 77)
					(self changeState: 10)
				else
					(= seconds 7)
				)
				(Print 550 20)
				(if (>= filthLevel 3)
					(Print 550 21 #at -1 144)
				)
			)
			(7
				(Print 550 22)
				(User canInput: TRUE)
			)
			(8
				(Ok)
				(HandsOff)
				(ego setLoop: 1 setCel: 255 setCycle: BegLoop self)
			)
			(9
				(= currentStatus egoNORMAL)
				(aLog show:)
				(NormalEgo 2 551)
				(ego
					viewer: PattiViewer
					posn: (+ (aLog x?) 20) (+ (aLog y?) -14)
				)
			)
			(10
				(theGame changeScore: 20)
				(ego
					cycleSpeed: 0
					setStep: 2 1
					setMotion: MoveTo 278 106 self
				)
				(= cycles 11)
			)
			(11
				(Print 550 23)
				(Print 550 24)
			)
			(12
				(ego setLoop: 3 setMotion: MoveTo 348 73 self)
			)
			(13
				(Print 550 25)
				(curRoom newRoom: 560)
			)
			(14
				(HandsOff)
				(= currentStatus egoDROWNING)
				(Print
					(Format @msgBuf 550 26 expletive)
					#at -1 10
					#dispose
				)
				(= drownLoop 0)
				(ego
					viewer: drowningViewer
					illegalBits: 0
					ignoreActors:
					view: 812
					setLoop: drownLoop
					setCycle: Forward
					setMotion: MoveTo (if (> (ego x?) 278) (ego x?) else 278) 106 self
				)
				(soundFX number: 6 loop: -1 play:)
			)
			(15
				(cls)
				(= drownLoop loopS)
				(ego setMotion: MoveTo 348 73 self)
			)
			(16
				(theGame setScript: (ScriptID DYING))
				((ScriptID DYING)
					caller: 816
					register: (Format @msgBuf 550 27)
					next: (Format @titleBuf 550 28)
				)
			)
		)
	)
	
	(method (handleEvent event)
		(if (or (!= (event type?) saidEvent) (event claimed?))
			(return)
		)
		(cond 
			((Said 'bang,board,(climb,lie,get<on)/log')
				(cond 
					((!= currentStatus egoNORMAL)
						(GoodIdea)
					)
					((not (< (ego distanceTo: aLog) 25))
						(NotClose)
					)
					(else
						(self changeState: 4)
					)
				)
			)
			((Said 'stand,exit,(get<off),(climb,get<off,up),(get<up),(stand<up)')
				(if (!= currentStatus 550)
					(Print 550 0)
				else
					(self changeState: 8)
				)
			)
			((Said 'weave')
				(Print 550 1)
			)
			((Said '/island')
				(Print 550 2)
				(Print 550 3 #at -1 144)
			)
			((Said 'attack,attack,grasp,grab/log')
				(cond 
					((not (< (ego distanceTo: aLog) 25))
						(NotClose)
					)
					((== currentStatus 550)
						(Print 550 4)
					)
					(else
						(Print 550 5)
					)
				)
			)
			((Said 'drag,drag,use/log')
				(cond 
					((not (< (ego distanceTo: aLog) 25))
						(NotClose)
					)
					((== currentStatus 550)
						(Print 550 6)
					)
					((!= (aLog x?) 77)
						(Print 550 7)
					)
					(else
						(self changeState: 1)
					)
				)
			)
			((Said 'drink')
				(Print 550 8)
			)
			(
				(or
					(Said 'board,exit,exit,(get<in)/water')
					(Said 'swim,wade')
				)
				(Print 550 9)
			)
			((Said 'get>')
				(cond 
					((Said '/log')
						(Print 550 10)
					)
					((Said '/palm')
						(Print 550 11)
					)
					((Said '/bush')
						(Print 550 12)
					)
				)
			)
			((Said 'look<in/bush')
				(Print 550 13)
			)
			((Said 'look>')
				(cond 
					((Said '/log')
						(cond 
							((!= (aLog x?) 77)
								(Print 550 14)
							)
							((== (ego view?) currentEgoView)
								(Print 550 15)
								(Print 550 16
									#at -1 144
								)
							)
							(else
								(Print 550 13)
							)
						)
					)
					((Said '/bush')
						(Print 550 17)
					)
					((Said '/palm')
						(Print 550 18)
					)
					((Said '[/area,water,creek]')
						(Print 550 19)
					)
				)
			)
		)
	)
)

(instance aLog of Prop
	(properties
		y 155
		x 77
		yStep 12
		view 550
		cycleSpeed 2
	)
)

(instance PattiViewer of Code
	(method (doit)
		(ego brBottom: (+ (ego y?) 1))
		(ego brTop: (- (ego brBottom?) (ego yStep?)))
		(ego brLeft: (- (ego x?) 8))
		(ego brRight: (+ (ego x?) 8))
		(cond 
			((& (ego onControl: origin) cRED)
				(RoomScript changeState: 14)
			)
			((& (ego onControl:) cGREEN)
				(ego view: currentEgoView setCycle: Walk)
			)
			((& (ego onControl: origin) cBLUE)
				(ego view: 552 setCycle: Walk)
			)
			((& (ego onControl: origin) cCYAN)
				((Inventory at: iDress) view: 32)
				(ego view: 551 setCycle: Forward)
			)
		)
	)
)

(instance drowningViewer of Code
	(method (doit)
		(if (Random 0 5)
			(ego setLoop: (+ drownLoop (Random 0 1)))
		)
	)
)
