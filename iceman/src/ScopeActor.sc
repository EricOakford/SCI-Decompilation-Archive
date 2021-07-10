;;; Sierra Script 1.0 - (do not remove this comment)
(script# 26)
(include sci.sh)
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
(instance scopeViewRm of Rm
	(properties
		picture 26
	)
	
	(method (init)
		(if (== (ego x?) 104) (= local3 1))
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
			((Said 'down/periscope') (self newRoom: 25))
			((Said 'look[/periscope]') (Print 26 0))
		)
	)
)

(class ScopeActor of Act
	(properties
		y 0
		x 0
		z 0
		heading 0
		yStep 2
		view 0
		loop 0
		cel 0
		priority 0
		underBits 0
		signal $0000
		nsTop 0
		nsLeft 0
		nsBottom 0
		nsRight 0
		lsTop 0
		lsLeft 0
		lsBottom 0
		lsRight 0
		brTop 0
		brLeft 0
		brBottom 0
		brRight 0
		cycleSpeed 0
		script 0
		cycler 0
		timer 0
		illegalBits $8000
		xLast 0
		yLast 0
		xStep 3
		moveSpeed 0
		blocks 0
		baseSetter 0
		mover 0
		looper 0
		viewer 0
		avoider 0
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
					(event claimed: 0)
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
					(event claimed: 0)
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
		signal $4000
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
	(properties)
	
	(method (doit &tmp [temp0 3])
		(Display
			26
			3
			dsCOORD
			225
			151
			dsCOLOR
			8
			dsALIGN
			-1
			dsWIDTH
			25
			dsFONT
			30
		)
		(Display
			(Format @temp0 26 4 submarineAbsHeading)
			dsCOORD
			225
			151
			dsCOLOR
			12
			dsALIGN
			-1
			dsWIDTH
			25
			dsFONT
			30
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
					(== (event type?) evJOYSTICK)
					(== (event message?) JOY_LEFT)
				)
				(self setScript: (Clone upKeyScript) self 0 setCel: 4)
			)
			((MousedOn self event) (self setScript: upButtonScript self 0 setCel: 4))
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
					(== (event type?) evJOYSTICK)
					(== (event message?) JOY_RIGHT)
				)
				(self setScript: (Clone upKeyScript) self 1 setCel: 3)
			)
			((MousedOn self event) (self setScript: upButtonScript self 1 setCel: 3))
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
					(== (event type?) evJOYSTICK)
					(== (event message?) JOY_DOWN)
				)
				(self setScript: downScopeScript)
			)
			((MousedOn self event) (self setScript: downScopeScript))
		)
	)
)

(instance downScopeScript of Script
	(properties)
	
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
	(properties)
	
	(method (doit)
		(if (== ((User curEvent?) type?) 2)
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
	(properties)
	
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
