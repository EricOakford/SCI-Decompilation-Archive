;;; Sierra Script 1.0 - (do not remove this comment)
(script# 241)
(include sci.sh)
(use Main)
(use keyStuff_242)
(use Feature)
(use Game)
(use Actor)
(use System)

(public
	rm241 0
	proc241_1 1
	proc241_2 2
	proc241_3 3
	proc241_4 4
)

(local
	local0
	local1
	local2
	local3
)
(procedure (proc241_1 param1)
	(= local0 param1)
	(= local3 param1)
	(switch local2
		(0
			(if (or (!= local3 10) (== global164 1))
				(if (== global164 1)
					(= global164 6)
				else
					(= global164 3)
				)
			else
				(= global164 1)
			)
		)
		(2
			(if (or (!= local3 10) (!= global164 1))
				(if (OneOf global164 0 2)
					(= global164 5)
				else
					(= global164 4)
				)
			else
				(= global164 2)
			)
		)
		(else  (= global164 7))
	)
	(curRoom setScript: countDownClock)
)

(procedure (proc241_2 param1)
	(if argc (= local2 param1))
	(return local2)
)

(procedure (proc241_3 param1)
	(if argc (= local0 param1))
	(return local0)
)

(procedure (proc241_4 param1)
	(PalVary pvUNINIT)
	(PalVary pvINIT param1 5)
)

(instance rm241 of Rm
	(properties
		noun 1
		picture 47
		style $8007
	)
	
	(method (init)
		(super init: &rest)
		(if (== global164 1)
			(Palette palSET_FROM_RESOURCE 471 2)
			(PalVary pvINIT 471 0)
		)
		(if (<= global164 8) (face init: stopUpd:))
		(chamberControls init: setOnMeCheck: 1 2)
		(topControlf init: setOnMeCheck: 1 16)
		(chamberf init: setOnMeCheck: 1 32)
		(self setScript: startAll)
	)
	
	(method (doit)
		(if (and (GameIsRestarting) (== global164 1))
			(Palette palSET_FROM_RESOURCE 471 2)
			(PalVary pvINIT 471 0)
		)
		(super doit: &rest)
	)
	
	(method (dispose)
		(theMusic1 fade: 0 10 5 1)
		(PalVary pvUNINIT)
		(DisposeScript 242)
		(super dispose: &rest)
	)
	
	(method (doVerb theVerb)
		(cond 
			((not (InRect 53 16 258 168 mouseX mouseY))
				(if (!= script countDownClock)
					(theMusic1 stop:)
					(self newRoom: 240)
				)
			)
			((== theVerb 1)
				(messager
					say:
						noun
						1
						(switch global164
							(0
								(switch global118
									(1 11)
									(2 20)
									(else  21)
								)
							)
							(1
								(switch global118
									(1 10)
									(2 18)
									(else  19)
								)
							)
							(8 9)
							(2
								(switch global118
									(1 12)
									(2 16)
									(else  17)
								)
							)
						)
						0
				)
			)
			(else (super doVerb: theVerb &rest))
		)
	)
)

(instance face of Prop
	(properties
		x 122
		y 49
		view 263
		signal $4000
	)
	
	(method (init)
		(if (< global118 3)
			(self setCel: global118)
			(++ global118)
		else
			(self setCel: 3)
		)
		(super init: &rest)
	)
	
	(method (doVerb)
		(curRoom doVerb: &rest)
	)
)

(instance chamberControls of Feature
	(properties
		onMeCheck $0002
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(face setScript: useChamberControls)
			)
			(1
				(face setScript: useChamberControls)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance startAll of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic1 number: 101 loop: -1 play:)
				(= cycles 1)
			)
			(1
				(if (== global164 1)
					(Palette palSET_FROM_RESOURCE 471 2)
				)
				(theGame handsOn:)
				(theIconBar disable: 0 3 4 5 6)
				(self dispose:)
			)
		)
	)
)

(instance useChamberControls of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(= local1 1)
				(self setScript: (ScriptID 242 0) self local0)
			)
			(1
				(theGame handsOn:)
				(= local1 0)
				(self dispose:)
			)
		)
	)
)

(instance countDownClock of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic1 number: 225 loop: -1 play:)
				(= cycles 1)
			)
			(1 (= ticks 50))
			(2
				(= next endActionsScript)
				(-- local0)
				(if local1 (proc242_1))
				(cond 
					((not (OneOf global164 1 2))
						(if (or (not local0) (> (- local3 local0) 5))
							(self dispose:)
						else
							(= state (- state 2))
							(= cycles 1)
						)
					)
					((not local0) (self dispose:))
					(else (= state (- state 2)) (= cycles 1))
				)
			)
		)
	)
)

(instance endActionsScript of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic1 number: 241 loop: 1 play:)
				(if local1
					((ScriptID 242 0) caller: self cue:)
				else
					(= cycles 2)
				)
			)
			(1
				(switch global164
					(0 (= register 8))
					(1
						(SolvePuzzle 186 75)
						(= register 4)
					)
					(2
						(if
						(and (== eurekaCurLocation 8) (& global169 $0002))
							(SolvePuzzle 187 50)
							(= register 1)
						else
							(= register 13)
						)
					)
					(3 (= register 5))
					(4 (= register 2))
					(5 (= register 6))
					(6 (= register 3))
					(7 (= register 7))
				)
				(messager say: 1 0 register 0 self)
			)
			(2
				(if (OneOf register 4 1)
					(if (== register 1)
						(theMusic1 number: 225 loop: -1 play:)
						(proc241_4 47)
						(= seconds 5)
					else
						(theMusic1 stop:)
						(curRoom newRoom: 240)
					)
				else
					(theMusic1 stop:)
					(EgoDead 46)
				)
			)
			(3
				(theMusic1 stop:)
				(curRoom newRoom: 240)
			)
		)
	)
)

(instance topControlf of Feature
	(properties
		noun 3
		onMeCheck $0010
	)
	
	(method (doVerb theVerb)
		(if (== theVerb 1)
			(messager
				say: noun 1
				(switch global164
					(0 11)
					(1 10)
					(8 9)
					(2 12)
				) 0
			)
		else
			(super doVerb: theVerb &rest)
		)
	)
)

(instance chamberf of Feature
	(properties
		noun 4
		onMeCheck $0020
	)
)
