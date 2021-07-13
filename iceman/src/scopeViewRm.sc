;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include game.sh)
(use Main)
(use Intrface)
(use Submarine_806)
(use CyclingProp)
(use Game)
(use User)
(use Actor)
(use System)

(public
	scopeViewRm 0
)

(local
	[local0 2]
	submarineAbsHeading
	local3
)
(instance scopeViewRm of Room
	(properties
		picture 26
	)
	
	(method (init)
		(if (== (ego x?) 104)
			(= local3 1)
		)
		(self setRegions: 314)
		(super init:)
		(HandsOn)
		(scopeWaterSky init: setPri: 1)
		(scopeXHairs init: setPri: 15)
		(= submarineAbsHeading (Submarine absHeading:))
		(switch (((ScriptID 314) script?) state?)
			(15 (rig init:) (harbor init:))
			(8 (ship init:))
		)
		(headingGaugeView init: setPri: 14)
		(cloud1 init:)
		(cloud2 init:)
		(downButton init:)
		(leftButton init:)
		(rightButton init:)
		(headingGauge doit:)
		(sparkle1 init: setPri: 6)
		(sparkle2 init: setPri: 6)
		(sparkle3 init: setPri: 6)
		(movement1 init: setPri: 6)
		(movement2 init: setPri: 6)
		(movement3 init: setPri: 6)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'down/periscope')
				(self newRoom: 25)
			)
			((Said 'look[/periscope]')
				(Print 26 0)
			)
		)
	)
)

(class ScopeActor of Actor
	(properties
		degreesFromSub 0
	)
	
	(method (init)
		(super init: &rest)
		(self setPri: priority setLoop: findX:)
	)
	
	(method (doit)
		(self findX:)
		(super doit: &rest)
	)
	
	(method (findX)
		(self
			x: (+ 161 (* 4 (- degreesFromSub submarineAbsHeading)))
		)
	)
)

(instance rig of ScopeActor
	(properties
		y 40
		x 161
		view 26
		cel 4
		priority 10
		degreesFromSub 282
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[/periscope,rig]')
				(if (and (< 129 x) (< x 189))
					(Print 26 1)
				else
					(event claimed: FALSE)
				)
			)
		)
	)
)

(instance harbor of ScopeActor
	(properties
		y 38
		x 161
		view 26
		cel 5
		priority 10
		degreesFromSub 36
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[/periscope,harbor]')
				(if (and (< 119 x) (< x 199))
					(Print 26 2)
				else
					(event claimed: FALSE)
				)
			)
		)
	)
)

(instance cloud1 of ScopeActor
	(properties
		y 34
		view 26
		loop 5
		priority 9
	)
	
	(method (init)
		(self degreesFromSub: (Random 30 330) cel: (Random 0 1))
		(super init:)
	)
)

(instance cloud2 of ScopeActor
	(properties
		y 34
		view 26
		loop 6
		cel 1
		priority 9
	)
	
	(method (init)
		(self cel: (Random 0 1) degreesFromSub: (Random 30 330))
		(super init:)
	)
)

(instance scopeWaterSky of View
	(properties
		y 68
		x 159
		view 26
		loop 2
		cel 7
	)
)

(instance scopeXHairs of View
	(properties
		y 72
		x 159
		view 26
	)
)

(instance ship of ScopeActor
	(properties
		y 38
		view 26
		cel 3
		priority 10
		signal ignrAct
		illegalBits $0000
	)
)

(instance headingGaugeView of View
	(properties
		y 160
		x 246
		view 26
		loop 2
		cel 6
	)
)

(instance headingGauge of Code

	(method (doit &tmp [str 3])
		(Display 26 3
			p_at 225 151
			p_color vGREY
			p_mode teJustRight
			p_width 25
			p_font 30
		)
		(Display
			(Format @str 26 4 submarineAbsHeading)
			p_at 225 151
			p_color vLRED
			p_mode teJustRight
			p_width 25
			p_font 30
		)
	)
)

(instance leftButton of Prop
	(properties
		y 131
		x 73
		view 26
		loop 2
		cel 1
	)
	
	(method (init)
		(super init:)
		(self setPri: 15)
		(mouseDownHandler add: self)
		(directionHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) direction)
					(== (event message?) dirW)
				)
				(self setScript: (Clone upKeyScript) self 0 setCel: 4)
			)
			((MousedOn self event)
				(self setScript: upButtonScript self 0 setCel: 4)
			)
		)
	)
	
	(method (cue)
		(self setCel: 1)
	)
)

(instance rightButton of Prop
	(properties
		y 131
		x 246
		view 26
		loop 2
	)
	
	(method (init)
		(super init:)
		(self setPri: 15)
		(mouseDownHandler add: self)
		(directionHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) direction)
					(== (event message?) dirE)
				)
				(self setScript: (Clone upKeyScript) self 1 setCel: 3)
			)
			((MousedOn self event)
				(self setScript: upButtonScript self 1 setCel: 3)
			)
		)
	)
	
	(method (cue)
		(self setCel: 0)
	)
)

(instance downButton of Prop
	(properties
		y 162
		x 73
		view 26
		loop 2
		cel 2
	)
	
	(method (init)
		(super init:)
		(mouseDownHandler add: self)
		(directionHandler add: self)
		(self setPri: 15)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(directionHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			(
				(and
					(== (event type?) direction)
					(== (event message?) dirS)
				)
				(self setScript: downScopeScript)
			)
			((MousedOn self event)
				(self setScript: downScopeScript)
			)
		)
	)
)

(instance downScopeScript of Script
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(client setCel: 5)
				(= seconds 2)
			)
			(1 (curRoom newRoom: 25))
		)
	)
)

(instance upButtonScript of Script

	(method (doit)
		(if (== ((User curEvent?) type?) mouseUp)
			(self dispose:)
		else
			(= submarineAbsHeading
				(if register
					(umod (+ submarineAbsHeading 1) 360)
				else
					(umod (- submarineAbsHeading 1) 360)
				)
			)
			(headingGauge doit:)
		)
	)
)

(instance upKeyScript of Script

	(method (doit)
		(asm
			pushi    #type
			pushi    0
			pushi    #curEvent
			pushi    0
			class    User
			send     4
			send     4
			push    
			ldi      64
			ne?     
			bt       code_0ac2
			pushi    #message
			pushi    0
			pushi    #curEvent
			pushi    0
			class    User
			send     4
			send     4
			push    
			pToa     register
			bnt      code_0abc
			ldi      3
			jmp      code_0abe
code_0abc:
			ldi      7
code_0abe:
			ne?     
			bnt      code_0aca
code_0ac2:
			pushi    #dispose
			pushi    0
			self     4
			jmp      code_0afc
code_0aca:
			pToa     register
			bnt      code_0ae2
			pushi    2
			lsl      submarineAbsHeading
			ldi      1
			add     
			push    
			pushi    360
			calle    umod,  4
			jmp      code_0af2
code_0ae2:
			pushi    2
			lsl      submarineAbsHeading
			ldi      1
			sub     
			push    
			pushi    360
			calle    umod,  4
code_0af2:
			sal      submarineAbsHeading
			pushi    #doit
			pushi    0
			lofsa    headingGauge
			send     4
code_0afc:
			ret     
		)
	)
)

(instance sparkle1 of CyclingProp
	(properties
		y 39
		x 137
		view 26
		loop 1
	)
)

(instance sparkle2 of CyclingProp
	(properties
		y 40
		x 155
		view 26
		loop 1
	)
)

(instance sparkle3 of CyclingProp
	(properties
		y 38
		x 179
		view 26
		loop 1
	)
)

(instance movement1 of CyclingProp
	(properties
		y 61
		x 146
		view 26
		loop 3
	)
)

(instance movement2 of CyclingProp
	(properties
		y 59
		x 172
		view 26
		loop 3
	)
)

(instance movement3 of CyclingProp
	(properties
		y 54
		x 164
		view 26
		loop 3
	)
)
