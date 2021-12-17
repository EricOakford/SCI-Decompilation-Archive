;;; Sierra Script 1.0 - (do not remove this comment)
(script# 50500)
(include sci.sh)
(use Main)
(use TPRoom)
(use TPScript)
(use Script)
(use n64896)
(use Print)
(use Talker)
(use Motion)
(use Actor)

(public
	roJailCell 0
	toJudge 1
)

(instance poTorin of Prop
	(properties)
)

(instance poBoogle of Prop
	(properties)
)

(instance poDreep of Prop
	(properties)
)

(instance poJudge of Prop
	(properties
		x 318
		y 104
		priority 104
		fixPriority 1
		view -15033
		loop 1
	)
)

(instance voJudgeFace of View
	(properties
		x 318
		y 104
		priority 105
		fixPriority 1
		view -15033
		loop 2
	)
)

(instance toJudge of Talker
	(properties
		x 318
		y 104
		view -15033
		loop 2
		priority 200
	)
	
	(method (init)
		(= loop (voJudgeFace loop?))
		(voJudgeFace hide:)
		(super init: &rest)
	)
	
	(method (dispose)
		(voJudgeFace show:)
		(super dispose: &rest)
	)
)

(instance soCycleJudgeDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(voJudgeFace hide: setLoop: 5)
				(poJudge loop: 3 cel: 0 setCycle: End self)
			)
			(1
				(= ticks (poJudge cycleSpeed?))
			)
			(2
				(poJudge loop: 4 cel: 0)
				(voJudgeFace show:)
				(self dispose:)
			)
		)
	)
)

(instance soCycleJudgeUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(voJudgeFace hide: setLoop: 2)
				(poJudge loop: 6 cel: 0 setCycle: End self)
			)
			(1
				(= ticks (poJudge cycleSpeed?))
			)
			(2
				(poJudge loop: 1 cel: 0)
				(voJudgeFace show:)
				(self dispose:)
			)
		)
	)
)

(instance soCycleTorinUp of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poTorin loop: 8 cel: 0 setCycle: End self)
			)
			(1 (self dispose:))
		)
	)
)

(instance soCycleTorinDown of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(poTorin loop: 9 cel: 0 setCycle: End self)
			)
			(1 (self dispose:))
		)
	)
)

(instance soPlayMovie of TPScript
	(properties
		oMessageList 1
		nTextWidth 1
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom picture: -15036)
				(curRoom drawPic: -15036)
				(poTorin
					view: -15035
					loop: 0
					cel: 0
					posn: 146 203
					init:
					setCycle: End self
				)
			)
			(1
				(Prints {loop 1 not available})
				(self cue:)
			)
			(2
				(poDreep
					view: -15035
					loop: 2
					cel: 0
					posn: 431 95
					init:
					setCycle: End self
				)
			)
			(3
				(poDreep dispose:)
				(poTorin loop: 3 cel: 0 posn: 149 196 setCycle: End self)
			)
			(4 (messager say: 0 0 2 0 self))
			(5
				(poBoogle
					view: -15035
					loop: 4
					cel: 0
					posn: 532 191
					init:
					setCycle: End self
				)
			)
			(6
				(poTorin loop: 5 cel: 0 posn: 149 200 setCycle: End self)
				(messager say: 0 0 1 0 self)
			)
			(7)
			(8
				(poBoogle dispose:)
				(poDreep dispose:)
				(poTorin dispose:)
				(curRoom picture: -15035)
				(curRoom drawPic: -15035)
				(poBoogle
					view: -15034
					loop: 0
					cel: 0
					posn: 314 323
					init:
					setCycle: End self
				)
			)
			(9
				(poBoogle loop: 1 cel: 0 posn: 361 308 setCycle: End self)
				(messager say: 0 0 3 0 self)
			)
			(10)
			(11
				(poBoogle dispose:)
				(curRoom picture: -15036)
				(curRoom drawPic: -15036)
				(poTorin
					view: -15034
					loop: 2
					cel: 0
					posn: 430 255
					init:
					setCycle: End self
				)
				(messager say: 0 0 4 0 self)
			)
			(12)
			(13
				(curRoom picture: -15034)
				(curRoom drawPic: -15034)
				(poTorin view: -15033 loop: 0 cel: 0 posn: 305 296)
				(poJudge view: -15033 loop: 1 cel: 0 posn: 318 104 init:)
				(voJudgeFace
					view: -15033
					loop: 2
					cel: 0
					posn: 318 104
					init:
				)
				(messager say: 0 0 5 1 self)
			)
			(14
				(messager say: 0 0 5 2 self)
				(self setScript: soCycleTorinUp self)
			)
			(15)
			(16
				(self setScript: soCycleJudgeDown self)
			)
			(17
				(messager sayRange: 0 0 5 3 4 self)
				(self setScript: soCycleTorinDown self)
			)
			(18)
			(19
				(self setScript: soCycleJudgeUp self)
			)
			(20
				(messager say: 0 0 5 5 self)
			)
			(21
				(messager say: 0 0 5 6 self)
				(self setScript: soCycleTorinUp self)
			)
			(22)
			(23
				(messager say: 0 0 5 7 self)
			)
			(24
				(messager sayRange: 0 0 5 8 10 self)
				(self setScript: soCycleTorinDown self)
			)
			(25)
			(26
				(self setScript: soCycleJudgeDown self)
			)
			(27
				(messager say: 0 0 5 11 self)
			)
			(28
				(self setScript: soCycleJudgeUp self)
			)
			(29
				(messager say: 0 0 5 12 self)
			)
			(30
				(voJudgeFace hide:)
				(poJudge loop: 7 cel: 0 setCycle: CT 2 1 self)
			)
			(31
				(sound1 lThumbLoop: -15035)
				(poJudge setCycle: End self)
			)
			(32
				(poJudge loop: 7 cel: 0 setCycle: CT 2 1 self)
			)
			(33
				(sound1 lThumbLoop: -15035)
				(poJudge setCycle: CT 5 1 self)
			)
			(34
				(poTorin loop: 10 cel: 0 setCycle: End self)
			)
			(35 (curRoom newRoom: -15136))
		)
	)
	
	(method (rememberMessage)
		(curRoom newRoom: -15136)
	)
	
	(method (sayMessage)
		(poBoogle dispose:)
		(poTorin dispose:)
		(poDreep dispose:)
		(poJudge dispose:)
		(voJudgeFace dispose:)
		(curRoom setScript: self)
	)
)

(instance soPlayMovie2 of TPScript
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(curRoom picture: -15034)
				(curRoom drawPic: -15034)
				(music1 pageSize: -10536)
				(poTorin view: -15033 loop: 0 cel: 0 posn: 305 296 init:)
				(poJudge view: -15033 loop: 1 cel: 0 posn: 318 104 init:)
				(voJudgeFace
					view: -15033
					loop: 2
					cel: 0
					posn: 318 104
					init:
				)
				(messager say: 0 0 0 1 self -10536)
			)
			(1
				(voJudgeFace hide:)
				(poJudge loop: 7 cel: 0 setCycle: CT 2 1 self)
			)
			(2
				(sound1 lThumbLoop: -15035)
				(poJudge setCycle: End self)
			)
			(3
				(poJudge loop: 7 cel: 0 setCycle: CT 2 1 self)
			)
			(4
				(sound1 lThumbLoop: -15035)
				(poJudge setCycle: CT 5 1 self)
			)
			(5
				(poTorin dispose:)
				(poJudge dispose:)
				(curRoom picture: -10534)
				(curRoom drawPic: -10534)
				(messager say: 0 0 0 2 self -10536)
			)
			(6
				(poTorin
					view: -10536
					posn: 171 275
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
				(messager say: 0 0 0 3 0 -10536)
			)
			(7
				(sound1 lThumbLoop: -10534 self)
			)
			(8
				(sound1 lThumbLoop: -10533 self)
				((ScriptID 64017 0) set: 143)
				(if (not ((ScriptID 64017 0) test: 137))
					(((ScriptID 64001 0) get: 53) moveTo: -1)
					(ego get: ((ScriptID 64001 0) get: 54))
				)
			)
			(9
				(poTorin loop: 1 cel: 0 setCycle: End self)
			)
			(10
				(poTorin dispose:)
				(curRoom picture: -1)
				((curRoom plane?) back: 234)
				(curRoom drawPic: -1)
				(messager say: 0 0 0 4 self -10536)
			)
			(11
				(poTorin
					view: -14434
					loop: 0
					cel: 0
					init:
					setCycle: End self
				)
			)
			(12 (curRoom newRoom: -14436))
		)
	)
)

(instance roJailCell of TPRoom
	(properties
		picture -15036
	)
	
	(method (init)
		(super init: &rest)
		(music1 pageSize: -15036)
		(if ((ScriptID 64017 0) test: 142)
			(proc64896_1 0)
			(curRoom setScript: soPlayMovie2)
		else
			(curRoom setScript: soPlayMovie)
		)
	)
	
	(method (setWander)
	)
	
	(method (intoPouch)
	)
)
