;;; Sierra Script 1.0 - (do not remove this comment)
(script# 121)
(include sci.sh)
(use Main)
(use rm120)
(use CycleBet)
(use BalloonTalker)
(use PolyPath)
(use Motion)
(use Actor)
(use System)

(public
	parentsCartoonScr 0
	momText 1
	dadText 2
	lockjawChewGumScr 3
	climbPipeScr 4
)

(local
	local0
	local1
)
(instance mom of Actor
	(properties
		view 123
		signal $6810
	)
)

(instance dad of Actor
	(properties
		view 123
		signal $6810
	)
)

(instance momText of BalloonTalker
	(properties
		x 4
		y 74
		talkWidth 135
		tailPosn 4
	)
	
	(method (dispose)
		(mom setCycle: 0 cel: 0 forceUpd: stopUpd:)
		(super dispose: &rest)
	)
	
	(method (say)
		(mom setCycle: Fwd)
		(super say: &rest)
	)
)

(instance dadText of BalloonTalker
	(properties
		x 104
		y 68
		talkWidth 175
		tailPosn 3
	)
	
	(method (dispose)
		(dad setCycle: 0 cel: 0 forceUpd: stopUpd:)
		(super dispose: &rest)
	)
	
	(method (say)
		(dad setCycle: Fwd)
		(super say: &rest)
	)
)

(instance parentsCartoonScr of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic2 number: 1209 setLoop: 1 play: self)
				(ShakeScreen 5 (Random 1 3))
			)
			(1
				(mom x: 156 y: 83 setLoop: 1 cel: 0 setPri: 3 init:)
				(dad x: 97 y: 83 setLoop: 0 cel: 0 setPri: 3 init:)
				(= ticks 30)
			)
			(2
				(mom setMotion: MoveTo 144 83 self)
				(dad setMotion: MoveTo 121 83 self)
				((ScriptID 895 1) normal: 0)
			)
			(3)
			(4
				(theMusic2 number: 1209 setLoop: 1 play: self)
				(ShakeScreen 5 (Random 1 3))
				(cast eachElementDo: #stopUpd)
			)
			(5
				(messager say: 1 0 1 0 self 121)
			)
			(6
				(theMusic2 loop: 0)
				(mom setLoop: 6 cel: 0 setMotion: MoveTo 156 83 self)
				(dad cel: 0 setMotion: MoveTo 156 83 self)
			)
			(7 (mom x: 1000 dispose:))
			(8
				(dad x: 1000 dispose:)
				(= cycles 2)
			)
			(9 (self dispose:))
		)
	)
)

(instance lockjawChewGumScr of Script
	(properties)
	
	(method (dispose)
		((ScriptID 895 1) normal: 1)
		(theGame handsOn:)
		(super dispose: &rest)
		(DisposeScript 121)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(= local0 ((ScriptID 895 0) cycleSpeed?))
				(= local1 ((ScriptID 895 1) cycleSpeed?))
				(proc120_2 1)
				(Bset 6)
				(= cycles 2)
			)
			(1
				((ScriptID 895 0) setMotion: PolyPath 228 155 self)
			)
			(2
				((ScriptID 895 0) setHeading: 225)
				((ScriptID 895 1)
					normal: 0
					normalize:
					ignoreActors: 1
					setMotion: PolyPath 207 154 self
				)
				(theGame points: 196 1)
			)
			(3
				((ScriptID 895 1)
					setLoop: 8
					cel: 4
					setCycle: 0
					forceUpd:
				)
				((ScriptID 895 0) stopUpd:)
				(= cycles 3)
			)
			(4
				(messager say: 2 104 0 1 self 121)
			)
			(5
				((ScriptID 895 1) x: 1000 hide: stopUpd:)
				(ego
					view: 121
					setLoop: 2
					cel: 0
					cycleSpeed: 5
					posn: 215 154
					setCycle: End self
				)
			)
			(6
				(theMusic2 number: 1204 loop: 1 play: self)
				(ego setLoop: 5 cel: 0 setCycle: Fwd)
			)
			(7
				(theMusic2 number: 1205 loop: 1 play: self)
				(ego
					cycleSpeed: 4
					setLoop: 3
					cel: 0
					setCycle: CT 5 1 self
				)
			)
			(8)
			(9
				(theMusic2 number: 1208 loop: 1 play: self)
				(ego cel: 6)
			)
			(10 (= ticks 30))
			(11
				(theMusic2 number: 1203 loop: 1 play: self)
				(ego cycleSpeed: 6 cel: 9 setCycle: CycleBet 9 11 2)
				(= cycles 2)
			)
			(12
				((ScriptID 2000 3) winX: 70 winY: 116 tailPosn: 1)
				(messager say: 2 104 0 2 self 121)
			)
			(13
				((ScriptID 2000 3) winX: 0 winY: 0)
				(ego setCycle: End self)
			)
			(14)
			(15
				((= register (Prop new:))
					view: 121
					setLoop: 6
					cel: 0
					x: 218
					y: 143
					setPri: 15
					ignoreActors: 1
					init:
					cycleSpeed: 6
					setCycle: End self
				)
				(theMusic2 number: 949 loop: 1 play: self)
			)
			(16)
			(17
				(register dispose:)
				(ego
					cycleSpeed: 6
					setLoop: 4
					cel: 0
					x: (- (ego x?) 2)
					y: (+ (ego y?) 1)
					setCycle: CT 2 1 self
				)
			)
			(18
				(theMusic2 number: 1206 loop: 1 play: self)
				(ego setCycle: End self)
			)
			(19)
			(20
				((ScriptID 895 1)
					normalize:
					view: 838
					setLoop: 1
					cel: 0
					setCycle: End
					cycleSpeed: 6
					x: 207
					y: 154
					show:
					approachX: 207
					approachY: 166
				)
				(ego
					normalize: 800
					x: 228
					y: 155
					loop: 8
					cel: 5
					cycleSpeed: local0
				)
				((ScriptID 120 4) init: posn: 261 154)
				(proc120_2)
				(= seconds 3)
			)
			(21 (self dispose:))
		)
	)
)

(instance climbPipeScr of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 121)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				((ScriptID 895 1) normal: 0)
				(ego
					ignoreActors: 1
					illegalBits: 0
					setMotion: PolyPath 64 117 self
				)
			)
			(1
				(ego setLoop: 6 setMotion: MoveTo 67 116 self)
			)
			(2
				(ego loop: 8 cel: 6 heading: 45)
				(= ticks 30)
			)
			(3
				(messager say: 3 7 2 0 self 121)
			)
			(4
				(self setScript: parentsCartoonScr self)
			)
			(5
				(proc120_2 1)
				((ScriptID 895 1)
					normalize:
					ignoreActors: 1
					illegalBits: 0
					setMotion: PolyPath 46 115 self
				)
			)
			(6
				((ScriptID 895 1)
					setLoop: 8
					setCycle: 0
					cel: 6
					x: 46
					y: 115
				)
				(= ticks 60)
			)
			(7
				((ScriptID 895 1) hide:)
				((ScriptID 895 0)
					view: 122
					setLoop: 2
					cel: 0
					cycleSpeed: 4
					setCycle: End self
				)
			)
			(8
				(messager say: 1 7 3 1 self 121)
			)
			(9
				((ScriptID 895 0) setCycle: Beg self)
			)
			(10
				((ScriptID 895 1) show:)
				((ScriptID 895 0)
					normalize:
					loop: 8
					cel: 6
					heading: 45
					x: 67
					y: 116
				)
				(= ticks 90)
			)
			(11
				((ScriptID 895 0) setHeading: 125 self)
			)
			(12 (= cycles 2))
			(13
				((ScriptID 895 0)
					setLoop: 4
					setMotion: MoveTo 85 136 self
				)
			)
			(14
				((ScriptID 895 0) setHeading: 270 self)
				((ScriptID 895 1) cel: 0)
			)
			(15
				((ScriptID 895 1) cel: 4)
				(= ticks 30)
			)
			(16
				(messager say: 1 7 3 2 5 self 121)
			)
			(17
				((ScriptID 895 1)
					normalize:
					setLoop: 5
					setMotion: MoveTo 23 129 self
				)
			)
			(18
				((ScriptID 895 1)
					view: 838
					setLoop: 1
					cel: 0
					setCycle: End
				)
				(ego setMotion: PolyPath 45 111 self)
			)
			(19
				(ego setMotion: MoveTo 49 104 self)
			)
			(20
				(ego setPri: 1 setHeading: 180 self)
			)
			(21 (= cycles 2))
			(22
				(messager say: 1 7 3 6 self 121)
			)
			(23
				(theGame setEgo: (ScriptID 895 1))
				((ScriptID 120 3) approachX: 17 approachY: 116)
				(= cycles 2)
			)
			(24
				(ego normalize:)
				(theGame handsOn:)
				(SetCursor 150 0)
				(self dispose:)
			)
		)
	)
)
