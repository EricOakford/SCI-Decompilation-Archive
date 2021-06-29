;;; Sierra Script 1.0 - (do not remove this comment)
(script# 875)
(include game.sh)
(use Main)
(use CycleBet)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	proc875_0 0
	smell 1
)

(local
	[local0 9]
)
(procedure (proc875_0 param1 param2 param3 &tmp theView theLoop theCel theX theY newEvent temp6)
	(= theView 0)
	(thinkBalloon
		setScale:
		scaleX: 75
		scaleY: 75
		x: ((ScriptID 895 1) x?)
		y: (- ((ScriptID 895 1) y?) 60)
	)
	(switch param1
		(1
			(thinkBalloon
				x: (- ((ScriptID 895 1) x?) 64)
				y: (- ((ScriptID 895 1) y?) 110)
			)
			(= theView 907)
			(= theLoop 1)
			(= theCel 7)
			(= theX (+ (thinkBalloon x?) 41))
			(= theY (+ (thinkBalloon y?) 18))
		)
		(2
			(thinkBalloon
				x: (- ((ScriptID 895 1) x?) 64)
				y: (- ((ScriptID 895 1) y?) 110)
			)
			(= theView 425)
			(= theLoop 1)
			(= theCel 0)
			(= theX (+ (thinkBalloon x?) 59))
			(= theY (+ (thinkBalloon y?) 32))
		)
		(3
			(thinkBalloon
				view: (if (== curRoomNum 240) 240 else 437)
				setScale: 0
			)
		)
		(4
			(thinkBalloon view: 436 setScale: 0)
		)
		(5
			(thinkBalloon view: 432 setScale: 0)
		)
		(6
			(thinkBalloon view: 435 setScale: 0)
		)
		(17
			(thinkBalloon view: 431 setScale: 0)
		)
		(18
			(thinkBalloon view: 433 setScale: 0)
		)
		(17
			(thinkBalloon view: 436 setScale: 0)
		)
	)
	(thinkBalloon init: stopUpd:)
	(thinkTail
		x: ((ScriptID 895 1) x?)
		y: (- (- ((ScriptID 895 1) y?) 30) 100)
		z: -100
		setScale:
		scaleX: 75
		scaleY: 75
		init:
		stopUpd:
	)
	(if theView
		(thinkObject
			view: theView
			setLoop: theLoop
			cel: theCel
			x: theX
			y: theY
			init:
			stopUpd:
		)
	)
	(Animate (cast elements?) FALSE)
	(if (< argc 3) (= param3 400))
	(= temp6 (+ param3 gameTime))
	(while
		(and
			(<= (- gameTime temp6) 0)
			(not (OneOf ((= newEvent (Event new:)) type?) 4 1))
		)
		(= gameTime (+ tickOffset (GetTime)))
		(newEvent dispose:)
	)
	(if (IsObject newEvent) (newEvent dispose:))
	(thinkBalloon dispose:)
	(thinkObject dispose:)
	(thinkTail dispose:)
	(Animate (cast elements?) FALSE)
	(if (> argc 1) (param2 cue:))
	(if (not (smell client?)) (DisposeScript 875))
)

(instance thinkTail of View
	(properties
		view 914
		cel 3
		priority 15
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance thinkObject of View
	(properties
		priority 15
		signal (| ignrAct ignrHrz fixPriOn)
	)
)

(instance thinkBalloon of View
	(properties
		view 915
		priority 15
		signal (| ignrAct ignrHrz fixedLoop fixPriOn)
	)
)

(instance smell of Script
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 875)
	)
	
	(method (changeState newState &tmp theRegister temp1 temp2)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= theRegister register)
				(= temp1 0)
				(while (<= temp1 8)
					(= [local0 temp1] (WordAt theRegister temp1))
					(++ temp1)
				)
				((ScriptID 895 1)
					normalize:
					ignoreActors: TRUE
					illegalBits: 0
					setMotion: PolyPath [local0 0] [local0 1] self
				)
			)
			(1
				((ScriptID 895 1)
					view: 808
					setLoop: [local0 2]
					setCel: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				(theMusic2 number: 911 setLoop: 1 play: self)
				((ScriptID 895 1) setCycle: CycleBet 4 5 -1)
			)
			(3
				(if [local0 8]
					(proc875_0 [local0 8] self)
				else
					(= cycles 2)
				)
			)
			(4
				((ScriptID 895 1) setCycle: BegLoop self)
			)
			(5
				((ScriptID 895 1)
					normalize:
					loop: 8
					cel: (if (== [local0 2] 3) 1 else 0)
				)
				(= ticks 15)
			)
			(6
				(if [local0 3]
					(messager
						say: [local0 3] [local0 4] [local0 5] [local0 6] self [local0 7]
					)
				else
					(= cycles 2)
				)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)
