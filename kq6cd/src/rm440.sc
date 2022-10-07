;;; Sierra Script 1.0 - (do not remove this comment)
(script# 440)
(include sci.sh)
(use Main)
(use minotaur)
(use KQ6Room)
(use Kq6Procs)
(use Conv)
(use RandCyc)
(use PolyPath)
(use Polygon)
(use Feature)
(use Motion)
(use Actor)
(use System)

(public
	rm440 0
)

(local
	local0
	local1
	local2
	local3
)
(instance myConv of Conversation
	(properties)
)

(instance rm440 of KQ6Room
	(properties
		picture 440
		style $000a
		walkOffEdge 1
		autoLoad 0
	)
	
	(method (init)
		(if (Btst 1)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init:
							178
							157
							208
							157
							241
							151
							239
							157
							319
							157
							319
							0
							0
							0
							0
							181
							43
							181
							86
							151
							75
							151
							83
							148
							125
							145
							128
							151
							168
							147
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 0 185 296 185 265 173 248 163 319 163 319 186 0 189
						yourself:
					)
			)
		else
			(Load rsSCRIPT 441)
			(curRoom
				addObstacle:
					((Polygon new:)
						type: 2
						init: 295 0 40 179 0 179 0 0
						yourself:
					)
					((Polygon new:)
						type: 2
						init: 0 185 152 185 151 189 0 189
						yourself:
					)
			)
		)
		(super init: &rest)
		((ScriptID 30 0) cue:)
		(flames setCycle: RandCycle init:)
		(features
			add: alter toLabExit exitSkull alterSkulls toCliffsExit
			eachElementDo: #init
		)
		(if (Btst 1)
			(= local0 7)
		else
			(proc441_1)
			(= local2 8)
			(= local0 6)
		)
		(ego init: reset: actions: egoDoMinotaurCode)
		(self setScript: walkIn)
	)
	
	(method (doit)
		(cond 
			((self script?))
			(
				(and
					(not ((ScriptID 30 0) seenByMino?))
					(not (Btst 1))
					(== (ego onControl: 1) 4)
				)
				(proc441_2)
			)
			((== (ego onControl: 1) 256) (self setScript: fallInPit))
			((== (ego onControl: 1) 16384) (curRoom setScript: walkOut 0 0))
			((== (ego onControl: 1) 8192)
				(if (Btst 1)
					(curRoom setScript: walkOut 0 1)
				else
					(ego setMotion: 0 posn: (+ (ego x?) 2) (ego y?))
					(messager say: 11 3 8 1)
				)
			)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(actTimer dispose:)
		(DisposeScript 441)
		(super dispose:)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 2 1 local0 1)
				1
			)
			(5
				(messager say: 2 5 local0 1)
				1
			)
			(2
				(cond 
					((Btst 1) (messager say: 2 2 7 1))
					(((ScriptID 30 0) seenByMino?) (messager say: 2 2 9 1))
					(else (messager say: 2 2 8 0 self))
				)
				1
			)
			(else 
				(messager say: 2 0 local0 1 self)
				1
			)
		)
	)
	
	(method (cue)
		(if
			(and
				(not (Btst 1))
				(not ((ScriptID 30 0) seenByMino?))
			)
			(proc441_0)
		)
	)
)

(instance alter of Feature
	(properties
		x 60
		y 110
		noun 12
		onMeCheck $1000
	)
)

(instance toLabExit of Feature
	(properties
		x 10
		y 170
		noun 11
		onMeCheck $0400
	)
)

(instance toCliffsExit of Feature
	(properties
		x 330
		y 140
		noun 9
		onMeCheck $2000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(messager say: 9 1 local0 1 0 440)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance alterSkulls of Feature
	(properties
		x 60
		y 110
		noun 13
		onMeCheck $0800
	)
)

(instance exitSkull of Feature
	(properties
		x 330
		y 140
		noun 10
		onMeCheck $0200
	)
)

(instance flames of Prop
	(properties
		x 203
		y 147
		noun 6
		view 445
		priority 1
		signal $4010
		cycleSpeed 8
		detailLevel 3
	)
)

(instance walkIn of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if (== prevRoomNum 340)
					(ego posn: 310 160 setMotion: PolyPath 230 160 self)
				else
					(ego posn: -5 187 setMotion: MoveTo 38 184 self)
				)
			)
			(1
				(if (Btst 1)
					(self cue:)
				else
					(theMusic number: 440 setLoop: -1 play:)
					(ego setHeading: 0)
					(Bset 161)
					(soundFx2 number: 433 setLoop: 1 play:)
					(= cycles 10)
				)
			)
			(2
				(if (not (Btst 1))
					(if (Btst 142)
						(messager say: 1 0 19 0 self 440)
					else
						(messager say: 1 0 1 0 self 440)
					)
				else
					(self cue:)
				)
			)
			(3
				(theGame handsOn:)
				(if (not (Btst 1))
					((ScriptID 30 0) setScript: actTimer)
				)
				(self dispose:)
			)
		)
	)
)

(instance walkOut of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(if register
					(ego setMotion: PolyPath 315 (ego y?) self)
				else
					(ego setMotion: MoveTo -15 (ego y?) self)
				)
			)
			(1
				(theGame handsOn:)
				(if register
					(curRoom newRoom: 340)
				else
					((ScriptID 30 0) prevEdgeHit: 4)
					(curRoom newRoom: 409)
				)
			)
		)
	)
)

(instance fallInPit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(ego
					view: 441
					normal: 0
					setLoop: (if (< (ego x?) 185) 5 else 4)
					cel: 0
					setMotion: 0
					cycleSpeed: 2
				)
				(= cycles 6)
			)
			(1 (messager say: 6 3 0 1 self))
			(2 (messager say: 6 3 0 2 self))
			(3
				(ego
					setPri: (if (< (ego x?) 185) 2 else -1)
					setCycle: EndLoop
				)
				(theMusic stop:)
				(soundFx2 number: 442 setLoop: 1 play: self)
			)
			(4 (= ticks 4))
			(5 (= seconds 2))
			(6 (EgoDead 15))
		)
	)
)

(instance actTimer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= seconds 20))
			(1
				(messager say: 1 0 18 1 self)
			)
			(2 (proc441_0) (self dispose:))
		)
	)
)

(instance egoDoMinotaurCode of Actions
	(properties)
	
	(method (doVerb theVerb)
		(return
			(switch theVerb
				(5 (return 0))
				(1 (return 0))
				(2 (return 0))
				(17
					(if (Btst 1)
						(messager say: 3 17 7 1)
					else
						(messager say: 3 17 6 1)
					)
					(return 1)
				)
				(72
					(if (== (curRoom script?) (ScriptID 441 3))
						((ScriptID 30 0) scarfOnMino: 1)
						(ego view: 441 normal: 0 setLoop: 0 cel: 0)
						(UnLoad 128 900)
						((ScriptID 441 4) cycleSpeed: 6 setCycle: Forward)
						((ScriptID 441 3) state: 19 register: 72 cue:)
						(theGame handsOff:)
						(theGame givePoints: 3)
					)
				)
				(else 
					(if (not (Btst 1))
						(messager say: 3 0 6 1)
						(return 1)
					else
						(messager say: 0 0 184 1 0 899)
						(return 1)
					)
				)
			)
		)
	)
)
