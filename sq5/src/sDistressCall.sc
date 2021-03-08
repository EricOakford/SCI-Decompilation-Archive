;;; Sierra Script 1.0 - (do not remove this comment)
(script# 211)
(include sci.sh)
(use Main)
(use rm201)
(use Osc)
(use ForCount)
(use Motion)
(use Actor)
(use System)

(public
	sQuirkStarCon 0
	sDistressCall 1
	sQuirkClorox 2
	sQuirkPuked 3
)

(instance sDistressCall of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 211)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 33)
				(= seconds 2)
			)
			(1 (proc201_6 self))
			(2
				(messager say: 9 0 0 1 self 202)
			)
			(3
				(self setScript: (ScriptID 201 4) self)
			)
			(4
				(self setScript: (ScriptID 209 0) self 210)
			)
			(5
				(messager say: 9 0 0 2 self 202)
				(theMusic1 number: 28 loop: -1 play:)
			)
			(6
				(quirkScaredMouth init: setCycle: ForwardCounter 5 self)
			)
			(7
				(messager say: 9 0 0 3 self 202)
			)
			(8
				(quirkPuke init: setCycle: Osc 4 self)
			)
			(9
				(quirkPuke dispose:)
				(quirkScaredMouth dispose:)
				(self setScript: (ScriptID 209 1) self)
			)
			(10 (= seconds 1))
			(11 (proc201_6 self))
			(12
				(messager say: 9 0 0 4 self 202)
			)
			(13
				(messager say: 9 0 0 5 self 202)
			)
			(14
				(self setScript: (ScriptID 201 4) self)
				(theMusic1 fade:)
			)
			(15
				(messager say: 16 0 0 1 self 202)
			)
			(16
				(curRoom newRoom: 215)
				(self dispose:)
			)
		)
	)
)

(instance sQuirkPuked of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 211)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(proc201_21)
				(= seconds 2)
			)
			(1 (proc201_6 self))
			(2
				(messager say: 17 0 16 1 self 202)
			)
			(3
				(self setScript: (ScriptID 201 4) self)
			)
			(4
				(self setScript: (ScriptID 209 0) self 204)
			)
			(5
				(messager say: 17 0 0 1 self 202)
			)
			(6
				(self setScript: (ScriptID 209 1) self)
			)
			(7 (self dispose:))
		)
	)
)

(instance sQuirkStarCon of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 211)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(self setScript: (ScriptID 209 0) self 209)
			)
			(1
				(messager say: 21 0 35 0 self)
				(Bset 34)
			)
			(2
				(self setScript: (ScriptID 209 1) self)
			)
			(3
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance sQuirkClorox of Script
	(properties)
	
	(method (dispose)
		(super dispose: &rest)
		(DisposeScript 211)
	)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(Bset 92)
				(SolvePuzzle 179 10)
				(proc201_21)
				(= cycles 1)
			)
			(1
				(self setScript: (ScriptID 209 0) self 209)
			)
			(2
				(messager say: 7 0 0 0 self 202)
			)
			(3
				(self setScript: (ScriptID 209 1) self)
			)
			(4
				(if (Btst 97)
					(theGame handsOn:)
					(self dispose:)
				else
					(proc201_7 self)
				)
			)
			(5
				(messager say: 7 0 6 1 self 202)
			)
			(6
				(self setScript: (ScriptID 201 8) self)
			)
			(7
				(theGame handsOn:)
				(self dispose:)
			)
		)
	)
)

(instance quirkScaredMouth of Prop
	(properties
		x 148
		y 88
		view 210
		loop 2
		priority 7
		signal $4010
	)
	
	(method (init)
		(self setCycle: Fwd)
		(super init: &rest)
	)
)

(instance quirkPuke of Prop
	(properties
		x 131
		y 79
		view 210
		loop 4
		priority 8
		signal $4010
	)
)
