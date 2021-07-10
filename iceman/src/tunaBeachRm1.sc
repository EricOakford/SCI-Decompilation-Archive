;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include sci.sh)
(use Main)
(use Intrface)
(use n802)
(use Sight)
(use Extra)
(use Game)
(use Actor)

(public
	tunaBeachRm1 0
	stupidAvoider 1
	stupidAvoider2 2
)

(local
	local0 =  1
)
(instance tunaBeachRm1 of Rm
	(properties
		picture 71
		north 74
		east 72
		south 46
		west 70
	)
	
	(method (init)
		(super init:)
		(Load rsVIEW 576)
		(self setRegions: 310 306)
		(if (== prevRoomNum 45) (globalSound stop:))
		(switch prevRoomNum
			(west
				(ego posn: 10 (ego y?) loop: 0)
			)
			(east
				(ego posn: 310 (ego y?) loop: 1)
			)
			(else 
				(ego posn: 70 105 view: 71 loop: 0)
			)
		)
		(ego init: observeControl: 64 cycleSpeed: 0)
		(addToPics add: rockPic doit:)
		(if (== (ego view?) 54) (ego view: 71))
		(wave init:)
		((Clone wave)
			x: 206
			y: 140
			loop: 1
			priority: 10
			isExtra: 1
			init:
		)
		((Clone wave)
			x: 303
			y: 161
			loop: 2
			priority: 10
			isExtra: 1
			init:
		)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and local0 (& (proc802_0 ego 3) $0040))
			(Print 71 0)
			(= local0 0)
		)
	)
	
	(method (handleEvent event)
		(cond 
			((super handleEvent: event))
			((Said 'look[<at,around][/room]') (Print 71 1))
		)
	)
)

(instance rockPic of PV
	(properties
		y 94
		x 261
		view 576
		loop 2
		cel 2
		signal $4000
	)
)

(instance wave of Extra
	(properties
		y 142
		x 71
		view 271
		priority 10
		cycleSpeed 3
		cycleType 1
		minPause 5
		maxPause 5
		minCycles 2
		maxCycles 3
	)
	
	(method (init)
		(super init:)
		(self isExtra: 1)
	)
	
	(method (handleEvent event param2)
		(asm
			pushi    #handleEvent
			pushi    1
			lsp      event
			super    Extra,  6
			bnt      code_0202
			jmp      code_0279
code_0202:
			pushi    3
			pushSelf
			lsp      event
			pushi    3
			calle    MousedOn,  6
			bnt      code_021b
			pushi    2
			pushi    71
			pushi    2
			calle    Print,  4
			jmp      code_0279
code_021b:
			pushi    1
			lofsa    '[/wave]>'
			push    
			callk    Said,  2
			bnt      code_0279
			pushi    1
			pushSelf
			calle    CantBeSeen,  2
			bnt      code_025b
			lsp      argc
			ldi      2
			ge?     
			bnt      code_023f
			lap      param2
			not     
			jmp      code_0241
code_023f:
			ldi      1
code_0241:
			bnt      code_025b
			pushi    1
			lofsa    '/*'
			push    
			callk    Said,  2
			bnt      code_0279
			pushi    2
			pushi    71
			pushi    3
			calle    Print,  4
			jmp      code_0279
code_025b:
			pushi    1
			lofsa    'look>'
			push    
			callk    Said,  2
			bnt      code_0279
			pushi    1
			lofsa    '[<at]'
			push    
			callk    Said,  2
			bnt      code_0279
			pushi    2
			pushi    71
			pushi    2
			calle    Print,  4
code_0279:
			ret     
		)
	)
)

(instance stupidAvoider of Feature
	(properties
		y 75
		x -15
	)
)

(instance stupidAvoider2 of Feature
	(properties
		y 105
		x -15
	)
)
