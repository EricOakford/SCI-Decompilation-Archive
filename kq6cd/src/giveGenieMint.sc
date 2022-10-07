;;; Sierra Script 1.0 - (do not remove this comment)
(script# 753)
(include sci.sh)
(use Main)
(use rm750)
(use Scaler)
(use Motion)
(use Actor)
(use System)

(public
	giveGenieMint 0
)

(local
	[local0 18] = [14 77 37 13 71 16 9 259 44 10 33 79 9 138 104 -1 -1 -1]
	local18
)
(instance giveGenieMint of Script
	(properties)
	
	(method (dispose)
		(super dispose:)
		(DisposeScript 752)
		(UnLoad 128 7501)
		(UnLoad 128 7503)
		(UnLoad 128 702)
		(DisposeScript 1012)
		(DisposeScript 753)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(messager say: 5 67 0 1 self)
			)
			(1
				(theGame givePoints: 1)
				(if (ego looper?) ((ego looper?) dispose:))
				(ego
					view: 7500
					setLoop: 2
					normal: 0
					setScale: 0
					cel: 0
					cycleSpeed: 8
					looper: 0
					setCycle: CycleTo 4 1 self
				)
			)
			(2
				((ScriptID 750 4)
					view: 702
					loop: 6
					cel: 0
					cycleSpeed: 7
					setCycle: CycleTo 4 1 self
				)
			)
			(3
				((ScriptID 750 4) setCycle: BegLoop self)
			)
			(4
				(messager say: 5 67 0 2 self)
			)
			(5
				((ScriptID 750 2) cel: 1 forceUpd:)
				(= cycles 4)
			)
			(6
				(messager say: 5 67 0 3 self)
			)
			(7
				(self setScript: (ScriptID 752 2) self (ScriptID 750 4))
			)
			(8
				(ego setCel: 255)
				((ScriptID 750 2) cel: 2 forceUpd:)
				((ScriptID 750 4)
					view: 752
					loop: 1
					cel: 0
					cycleSpeed: 8
					posn: 170 137
				)
				(= ticks 60)
			)
			(9
				(self setScript: (ScriptID 752 1) self (ScriptID 750 4))
			)
			(10
				((ScriptID 750 4) setCycle: CycleTo 1 1 self)
			)
			(11 (= cycles 2))
			(12
				(if (ego looper?) ((ego looper?) dispose:))
				(ego
					normal: 0
					view: 7500
					setLoop: 3
					setCycle: 0
					looper: 0
					setScale: Scaler 102 76 189 139
				)
				((ScriptID 750 4) setCycle: EndLoop self)
			)
			(13
				(messager say: 5 67 0 4 self)
			)
			(14
				(messager say: 5 67 0 5 self)
			)
			(15
				(messager say: 5 67 0 6 self)
			)
			(16
				((ScriptID 750 4)
					loop: 2
					cel: 8
					cycleSpeed: 4
					setCycle: CycleTo 2 1 self
				)
			)
			(17 (= cycles 10))
			(18
				((ScriptID 750 4) cel: 3)
				(= cycles 8)
			)
			(19
				((ScriptID 750 4) setCycle: CycleTo 5 1 self)
			)
			(20 (= cycles 10))
			(21
				(messager say: 5 67 0 7 self)
			)
			(22
				(messager say: 5 67 0 8 self)
			)
			(23
				((ScriptID 750 4) cycleSpeed: 6 setCycle: EndLoop self)
			)
			(24
				(theGlobalSound number: 707 setLoop: 1 play:)
				((ScriptID 750 4)
					view: 702
					loop: 7
					cel: 1
					x: (- ((ScriptID 750 4) x?) 13)
					cycleSpeed: 8
					setCycle: EndLoop self
				)
			)
			(25
				(theGlobalSound number: 708 setLoop: 1 play:)
				(dazzleBall init: setCycle: Forward)
				(= cycles 2)
			)
			(26
				(messager say: 5 67 0 9 self)
			)
			(27
				(if (!= [local0 local18] -1)
					(-- state)
					(dazzleBall
						loop: [local0 local18]
						setMotion: MoveTo [local0 (+ local18 1)] [local0 (+ local18 2)] self
					)
					(if (>= local18 3)
						(theGlobalSound number: 709 loop: 1 play:)
					)
					(= local18 (+ local18 3))
				else
					(= cycles 1)
				)
			)
			(28
				(messager say: 5 67 0 11 self)
			)
			(29
				(dazzleBall setMotion: MoveTo 160 111 self)
			)
			(30
				(dazzleBall
					loop: 15
					cel: 0
					cycleSpeed: 7
					setCycle: EndLoop self
				)
				(theGlobalSound number: 0 stop:)
				(theGlobalSound number: 753 setLoop: 1 play: self)
			)
			(31
				((ScriptID 750 4) dispose:)
				(dazzleBall dispose:)
			)
			(32
				(messager say: 5 67 0 12 self)
			)
			(33
				((ScriptID 750 2) dispose:)
				(if global169
					(curRoom drawPic: 750 15)
				else
					(curRoom drawPic: 750 100)
				)
				((ScriptID 750 9) addToPic:)
				((ScriptID 750 3)
					view: 751
					loop: 8
					cel: 0
					signal: 16384
					init:
					setCycle: CycleTo 4 1 self
				)
			)
			(34
				(theGlobalSound number: 652 setLoop: 1 play:)
				((ScriptID 750 3) setCycle: EndLoop self)
			)
			(35
				(messager say: 5 67 0 13 self)
			)
			(36
				(messager say: 5 67 0 14 self)
			)
			(37
				(proc750_5)
				(if (or (not howFast) (not (HaveMouse)))
					(= seconds 15)
				else
					(= seconds 8)
				)
			)
			(38
				(curRoom setScript: (ScriptID 754 1))
			)
		)
	)
)

(instance dazzleBall of Actor
	(properties
		x 150
		y 100
		yStep 7
		view 702
		loop 14
		priority 15
		signal $6810
		cycleSpeed 3
		illegalBits $0000
		xStep 7
		moveSpeed 0
	)
)
