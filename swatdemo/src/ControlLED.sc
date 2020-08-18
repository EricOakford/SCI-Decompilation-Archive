;;; Sierra Script 1.0 - (do not remove this comment)
(script# SWATCONTROLS)
(include game.sh)
(use Main)
(use Button)
(use Plane)
(use Actor)
(use System)

(public
	proc913_0 0
)

(local
	local0
	local1
)
(procedure (proc913_0 &tmp temp0 temp1 temp2 temp3)
	(asm
		ldi      0
		sal      local0
		sat      temp0
		sat      temp2
		sat      temp1
		sal      local1
		lag      cast
		sat      temp1
		pushi    #setRect
		pushi    4
		pushi    0
		pushi    0
		pushi    319
		pushi    #bottom
		pushi    0
		lag      thePlane
		send     4
		push    
		pushi    38
		pushi    1
		pushi    0
		pushi    109
		pushi    1
		pushi    65534
		pushi    7
		pushi    1
		pushi    500
		pushi    142
		pushi    0
		pushi    379
		pushi    1
		pushi    #new
		pushi    0
		class    Cast
		send     4
		sag      cast
		push    
		pushi    #new
		pushi    0
		class    Plane
		send     4
		sat      temp2
		send     40
		pushi    3
		pushi    2
		pushi    244
		pushi    50
		callk    RemapColors,  6
		pushi    #init
		pushi    0
		lofsa    paletteDown
		send     4
		pushi    #init
		pushi    0
		lofsa    controlView
		send     4
		pushi    #init
		pushi    0
		lofsa    questionMark
		send     4
		pushi    #init
		pushi    0
		lofsa    aboutButn
		send     4
		pushi    #init
		pushi    0
		pushi    596
		pushi    1
		pushi    1
		pushi    13
		callb    Btst,  2
		not     
		push    
		lofsa    saveMission
		send     10
		pushi    #init
		pushi    0
		pushi    596
		pushi    1
		pushi    1
		pushi    13
		callb    Btst,  2
		not     
		push    
		lofsa    restoreMission
		send     10
		pushi    #init
		pushi    0
		lofsa    playButn
		send     4
		pushi    #init
		pushi    0
		lofsa    quitButn
		send     4
		pushi    #init
		pushi    0
		lofsa    speedUpButn
		send     4
		pushi    #init
		pushi    0
		lofsa    speedDownButn
		send     4
		pushi    #init
		pushi    0
		lofsa    volumeUpButn
		send     4
		pushi    #init
		pushi    0
		lofsa    volumeDownButn
		send     4
		pushi    #init
		pushi    0
		lofsa    detailUpButn
		send     4
		pushi    #init
		pushi    0
		lofsa    detailDownButn
		send     4
		pushi    #init
		pushi    2
		lsg      theGame
		pushi    776
		lofsa    speedLed
		send     8
		pushi    #init
		pushi    2
		lsg      theGame
		pushi    534
		lofsa    volumeLed
		send     8
		pushi    #init
		pushi    2
		lsg      theGame
		pushi    339
		lofsa    detailLed
		send     8
		pushi    0
		callk    FrameOut,  0
		pushi    #setCursor
		pushi    0
		lag      theGame
		send     4
		sat      temp0
		pushi    #view
		pushi    1
		pushi    999
		pushi    15
		pushi    1
		pushi    0
		lag      normalCursor
		send     12
		pushi    #setCursor
		pushi    2
		lsg      normalCursor
		pushi    1
		lag      theGame
		send     8
		pushi    #new
		pushi    0
		pushi    #curEvent
		pushi    0
		lag      user
		send     4
		send     4
		sat      temp3
code_02aa:
		pushi    #type
		pushi    0
		lat      temp3
		send     4
		bnt      code_02c8
		pushi    #new
		pushi    0
		pushi    #curEvent
		pushi    0
		lag      user
		send     4
		send     4
		sat      temp3
		jmp      code_02aa
code_02c8:
		lal      local0
		not     
		bnt      code_031d
code_02cd:
		pushi    #type
		pushi    0
		lat      temp3
		send     4
		not     
		bt       code_02e7
		pushi    #type
		pushi    0
		lat      temp3
		send     4
		push    
		ldi      1
		and     
		not     
		bnt      code_02fb
code_02e7:
		pushi    #new
		pushi    0
		pushi    #curEvent
		pushi    0
		lag      user
		send     4
		send     4
		sat      temp3
		jmp      code_02cd
code_02fb:
		pushi    #eachElementDo
		pushi    2
		pushi    165
		lst      temp3
		lag      cast
		send     8
		pushi    #new
		pushi    0
		pushi    #curEvent
		pushi    0
		lag      user
		send     4
		send     4
		sat      temp3
		jmp      code_02c8
code_031d:
		pushi    #dispose
		pushi    0
		lag      cast
		send     4
		lat      temp1
		sag      cast
		pushi    #setCursor
		pushi    2
		lst      temp0
		pushi    1
		lag      theGame
		send     8
		pushi    #dispose
		pushi    0
		lat      temp2
		send     4
		pushi    1
		pushi    0
		callk    RemapColors,  2
		lal      local1
		ret     
	)
)

(instance paletteDown of View
	(properties
		priority 150
		fixPriority 1
		view 1
	)
	
	(method (init)
		(super init: &rest)
		(self
			scaleSignal: 1
			scaleX: (* (- (thePlane right:) (thePlane left:)) 128)
			scaleY: (* (- (thePlane bottom?) (thePlane top?)) 128)
		)
		(UpdateScreenItem self)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance controlView of View
	(properties
		priority 151
		fixPriority 1
		view 16
	)
	
	(method (init &tmp temp0 temp1)
		(= temp0 (CelWide view loop cel))
		(= temp1 (CelHigh view loop cel))
		(= x
			(/ (- (- (thePlane right:) (thePlane left:)) temp0) 2)
		)
		(= y
			(/ (- (- (thePlane bottom?) (thePlane top?)) temp1) 2)
		)
		(super init: &rest)
	)
	
	(method (onMe)
		(return 0)
	)
)

(instance questionMark of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 1
	)
	
	(method (init)
		(= x (+ (controlView x?) 59))
		(= y (+ (controlView y?) 81))
		(super init: &rest)
	)
)

(instance aboutButn of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 2
	)
	
	(method (init)
		(= x (+ (controlView x?) 16))
		(= y (+ (controlView y?) 81))
		(super init: &rest)
		(= active 0)
	)
	
	(method (doVerb)
		(= local0 1)
	)
)

(instance saveMission of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 3
	)
	
	(method (init)
		(= x (+ (controlView x?) 8))
		(= y (+ (controlView y?) 29))
		(super init: &rest)
	)
	
	(method (doVerb)
		(= local1 1)
		(= local0 1)
	)
)

(instance restoreMission of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 4
	)
	
	(method (init)
		(= x (+ (controlView x?) 8))
		(= y (+ (controlView y?) 46))
		(super init: &rest)
	)
	
	(method (doVerb)
		(= local1 2)
		(= local0 1)
	)
)

(instance playButn of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 5
	)
	
	(method (init)
		(= x (+ (controlView x?) 8))
		(= y (+ (controlView y?) 63))
		(super init: &rest)
	)
	
	(method (doVerb)
		(= local0 1)
	)
)

(instance quitButn of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 6
	)
	
	(method (init)
		(= x (+ (controlView x?) 52))
		(= y (+ (controlView y?) 63))
		(super init: &rest)
	)
	
	(method (doVerb)
		(= local0 1)
		(= quit 2)
	)
)

(instance speedUpButn of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 8
	)
	
	(method (init)
		(= x (+ (controlView x?) 115))
		(= y (+ (controlView y?) 42))
		(super init: &rest)
		(= signal (& signal $efff))
	)
	
	(method (doVerb)
		(speedLed adjustValue: 1)
	)
)

(instance volumeUpButn of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 8
	)
	
	(method (init)
		(= x (+ (controlView x?) 147))
		(= y (+ (controlView y?) 42))
		(super init: &rest)
		(= signal (& signal $efff))
	)
	
	(method (doVerb)
		(volumeLed adjustValue: 1)
	)
)

(instance detailUpButn of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 8
	)
	
	(method (init)
		(= x (+ (controlView x?) 179))
		(= y (+ (controlView y?) 42))
		(super init: &rest)
		(= signal (& signal $efff))
	)
	
	(method (doVerb)
		(detailLed adjustValue: 1)
	)
)

(instance speedDownButn of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 9
	)
	
	(method (init)
		(= x (+ (controlView x?) 115))
		(= y (+ (controlView y?) 92))
		(super init: &rest)
		(= signal (& signal $efff))
	)
	
	(method (doVerb)
		(speedLed adjustValue: -1)
	)
)

(instance volumeDownButn of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 9
	)
	
	(method (init)
		(= x (+ (controlView x?) 147))
		(= y (+ (controlView y?) 92))
		(super init: &rest)
		(= signal (& signal $efff))
	)
	
	(method (doVerb)
		(volumeLed adjustValue: -1)
	)
)

(instance detailDownButn of Button
	(properties
		priority 180
		fixPriority 1
		view 16
		loop 9
	)
	
	(method (init)
		(= x (+ (controlView x?) 180))
		(= y (+ (controlView y?) 92))
		(super init: &rest)
		(= signal (& signal $efff))
	)
	
	(method (doVerb)
		(detailLed adjustValue: -1)
	)
)

(class ControlLED of View
	(properties
		view 16
		loop 7
		monObj 0
		monSel 0
		value 0
		minValue 0
		maxValue 10
		increment 1
	)
	
	(method (init theMonObj theMonSel)
		(= monObj theMonObj)
		(= monSel theMonSel)
		(super init: &rest)
		(= increment (/ 30 maxValue))
		(self evalObj: displayValue:)
	)
	
	(method (evalObj param1)
		(if argc
			(= param1 (Max minValue (Min maxValue param1)))
			(Eval monObj monSel param1)
		)
		(Eval monObj monSel)
	)
	
	(method (adjustValue param1 &tmp controlLEDEvalObj)
		(= controlLEDEvalObj (self evalObj:))
		(self evalObj: (+ controlLEDEvalObj param1))
		(self displayValue:)
	)
	
	(method (displayValue)
		(= cel (* (self evalObj:) increment))
		(UpdateScreenItem self)
		(FrameOut)
	)
)

(instance speedLed of ControlLED
	
	(method (init)
		(= x (+ (controlView x?) 112))
		(= y (+ (controlView y?) 52))
		(super init: &rest)
	)
)

(instance volumeLed of ControlLED
	(properties
		maxValue 15
	)
	
	(method (init)
		(= x (+ (controlView x?) 145))
		(= y (+ (controlView y?) 52))
		(super init: &rest)
	)
)

(instance detailLed of ControlLED
	(properties)
	
	(method (init)
		(= x (+ (controlView x?) 177))
		(= y (+ (controlView y?) 52))
		(super init: &rest)
	)
)
