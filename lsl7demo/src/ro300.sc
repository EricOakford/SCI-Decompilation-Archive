;;; Sierra Script 1.0 - (do not remove this comment)
(script# 300)
(include sci.sh)
(use Main)
(use DialogPlane)
(use L7Room)
(use NewUser)
(use PushButton)
(use GenDialog)
(use Timer)
(use System)

(public
	ro300 0
)

(local
	local0
)
(instance ro300 of L7Room
	(properties
		picture -15536
	)
	
	(method (init)
		(super init: &rest)
		(gOEventHandler registerGlobalHandler: oAnyEventHandler)
		(= local0 -15536)
		(theGame handsOff:)
		(curRoom setScript: soShowSlides)
	)
	
	(method (dispose)
		(gOEventHandler unregisterGlobalHandler: oAnyEventHandler)
		(if (timers contains: oPicTimer)
			(oPicTimer dispose: delete:)
		)
		(super dispose: &rest)
	)
)

(instance voLabel of TextItem
	(properties
		x 120
		y 440
		fore 79
		back 0
		font 2510
		maxWidth 400
	)
	
	(method (init)
		(= text (MakeMessageText 0 0 1 1 300))
		(if (not text) (return))
		(super init: &rest)
	)
)

(instance soShowSlides of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(cond 
					((<= local0 -15534) (if (not (voLabel plane?)) (voLabel init:)))
					((voLabel plane?) (voLabel dispose:))
				)
				(if (u> local0 -15524)
					(= state (+ state 1))
				else
					(ro300 drawPic: local0)
				)
				(= cycles 1)
			)
			(1
				(++ local0)
				(= state (- state 2))
				(= ticks 180)
			)
			(2
				(ro300 drawPic: 12200)
				(= ticks 120)
			)
			(3
				(gOMusic1 fadeOut: 6 3)
				(proc64896_1 1 1 self)
			)
			(4
				(if ((ScriptID 64017 0) test: 8)
					(curRoom newRoom: 100)
				else
					(= quit 1)
				)
				(self dispose:)
			)
		)
	)
)

(instance oAnyEventHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp temp0)
		(if
			(or
				(== (event type?) evMOUSEBUTTON)
				(== (event type?) evKEYBOARD)
			)
			(if
				(and
					(<= emRIGHT_SHIFT (event modifiers?))
					(<= (event modifiers?) emALT)
				)
				(return 0)
			)
			(gOEventHandler unregisterGlobalHandler: oAnyEventHandler)
			(switch
				(= temp0
					(proc64033_7
						4
						0
						0
						(MakeMessageText 0 0 0 5 300)
						(MakeMessageText 0 0 0 2 300)
						(MakeMessageText 0 0 0 3 300)
						(MakeMessageText 0 0 0 4 300)
					)
				)
				(0 (= temp0 0))
				(1
					((ScriptID 64017 0) set: 5)
					((ScriptID 64017 0) set: 6)
					((ScriptID 64017 0) set: 7)
					((ScriptID 64017 0) clear: 8)
					(curRoom newRoom: 9000)
					(return 1)
				)
				(2
					((ScriptID 64017 0) set: 5)
					((ScriptID 64017 0) clear: 6)
					((ScriptID 64017 0) set: 7)
					((ScriptID 64017 0) set: 8)
				)
				(3
					((ScriptID 64017 0) set: 5)
					((ScriptID 64017 0) clear: 6)
					((ScriptID 64017 0) clear: 7)
					((ScriptID 64017 0) clear: 8)
					(= quit 1)
					(return 1)
				)
			)
			(gOEventHandler registerGlobalHandler: oAnyEventHandler)
		)
		(return 1)
	)
)

(instance oPicTimer of Timer
	(properties)
	
	(method (cue)
		(ro300 drawPic: local0)
		(if (> local0 -15524)
			(gOMusic1 fadeOut: 6 2)
			(if ((ScriptID 64017 0) test: 8)
				(curRoom newRoom: 100)
			else
				(= quit 1)
			)
		)
		(++ local0)
		(self setTicks: self 150)
	)
)
