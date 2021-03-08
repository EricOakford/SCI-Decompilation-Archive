;;; Sierra Script 1.0 - (do not remove this comment)
(script# 460)
(include sci.sh)
(use Main)
(use Sound)
(use Motion)
(use Game)
(use Actor)
(use System)

(public
	rm460 0
)

(local
	local0
	[local1 10]
	local11
	[local12 500]
	local512
	local513 =  3
	local514
	local515
)
(procedure (localproc_00b9 &tmp temp0 temp1 [temp2 6])
	(= temp1 1)
	(= temp0 0)
	(while (< temp0 5)
		(if
		(!= (StrAt @local1 temp0) (StrAt {80869} temp0))
			(= temp1 0)
			(break)
		)
		(++ temp0)
	)
	(return temp1)
)

(instance theMusic4 of Sound
	(properties)
)

(instance rm460 of Rm
	(properties
		picture 119
	)
	
	(method (init)
		(switch prevRoomNum
			(420
				(curRoom setScript: sComputer)
			)
			(else 
				(curRoom setScript: sComputer)
			)
		)
		(super init:)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (notify param1 param2 param3 param4 param5 param6)
		(Format
			@local1
			460
			0
			(+ param2 48)
			(+ param3 48)
			(+ param4 48)
			(+ param5 48)
			(+ param6 48)
		)
		(if (== param1 -1)
			(= local11 -1)
		else
			(= local11 (localproc_00b9))
		)
	)
)

(instance sComputer of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0 (= cycles 2))
			(1
				(previousIcon init:)
				(nextIcon init:)
				(exitIcon init:)
				(self setScript: (ScriptID 203 0) self 0)
			)
			(2
				(switch local11
					(1
						(theMusic4 number: 124 loop: 1 play:)
						(= local514 1)
						(= local11 0)
						(SolvePuzzle 221 20)
						(compHeading init:)
						(Message msgGET 460 2 0 0 1 @local12)
						(Display
							@local12
							dsCOORD
							50
							50
							dsCOLOR
							39
							dsBACKGROUND
							0
							dsFONT
							1605
							dsWIDTH
							240
						)
					)
					(0 (= state -1) (= cycles 1))
					(-1
						(theIconBar enable: 0 3 5 4 7)
						(curRoom newRoom: 420)
					)
					(else  (= cycles 1))
				)
				(= cycles 5)
			)
			(3
				(self setScript: resetIconbar self)
			)
			(4 (self dispose:))
		)
	)
)

(instance resetIconbar of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOn:)
				(= cycles 5)
			)
			(1
				(theIconBar disable: 0 1 3 4 7)
				(= cycles 3)
			)
			(2
				(theIconBar curIcon: (theIconBar at: 2))
				(theGame setCursor: ((theIconBar at: 2) cursor?))
				(self dispose:)
			)
		)
	)
)

(instance sComputerLog of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(switch local512
					(0
						(Message msgGET 460 2 0 0 1 @local12)
						(Display
							@local12
							dsCOORD
							50
							50
							dsCOLOR
							39
							dsBACKGROUND
							0
							dsFONT
							1605
							dsWIDTH
							240
						)
					)
					(1
						(Message msgGET 460 2 0 0 2 @local12)
						(Display
							@local12
							dsCOORD
							50
							50
							dsCOLOR
							39
							dsBACKGROUND
							0
							dsFONT
							1605
							dsWIDTH
							240
						)
					)
					(2
						(Message msgGET 460 2 0 0 3 @local12)
						(Display
							@local12
							dsCOORD
							50
							50
							dsCOLOR
							39
							dsBACKGROUND
							0
							dsFONT
							1605
							dsWIDTH
							240
						)
					)
					(3
						(Message msgGET 460 2 0 0 4 @local12)
						(Display
							@local12
							dsCOORD
							50
							50
							dsCOLOR
							39
							dsBACKGROUND
							0
							dsFONT
							1605
							dsWIDTH
							240
						)
					)
				)
				(= cycles 2)
			)
			(1
				(self setScript: resetIconbar self)
			)
			(2 (self dispose:))
		)
	)
)

(instance sExitIcon of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(exitIcon setCycle: Beg self)
			)
			(1
				(theIconBar enable: 0 3 5 4 6 7)
				(= cycles 1)
			)
			(2 (curRoom newRoom: 420))
		)
	)
)

(instance compHeading of Prop
	(properties
		x 119
		y 26
		view 627
		loop 13
		cel 1
		signal $4000
	)
)

(instance nextIcon of Prop
	(properties
		y 87
		view 627
		loop 8
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if local514
					(if (< local512 local513) (++ local512))
					(self setCycle: Beg)
					(curRoom setScript: sComputerLog)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance previousIcon of Prop
	(properties
		y 72
		view 627
		loop 6
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(if local514
					(if (> local512 0) (-- local512))
					(self setCycle: Beg)
					(curRoom setScript: sComputerLog)
				)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)

(instance exitIcon of Prop
	(properties
		y 102
		view 627
		loop 9
		signal $4000
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(4
				(curRoom setScript: sExitIcon)
			)
			(else 
				(super doVerb: theVerb &rest)
			)
		)
	)
)
