;;; Sierra Script 1.0 - (do not remove this comment)
(script# 910)
(include sci.sh)
(use Main)
(use Procs)
(use Plane)
(use String)
(use Array)
(use Print)
(use Game)
(use Actor)
(use System)

(public
	shiversLogo 0
)

(local
	local0
)
(instance buttonCast of Cast
	(properties)
)

(instance shiversLogo of Room
	(properties)
	
	(method (init &tmp temp0 temp1 temp2)
		(asm
			pushi    #handsOn
			pushi    0
			lag      theGame
			send     4
			pushi    1
			pushi    20402
			calle    proc951_7,  2
			pushi    1
			pushi    20402
			calle    proc951_9,  2
			pushi    #play
			pushi    4
			pushi    20402
			pushi    65535
			pushi    0
			pushi    0
			lag      sounds
			send     12
			pushi    #fade
			pushi    6
			pushi    20402
			pushi    66
			pushi    1
			pushi    40
			pushi    0
			pushi    0
			lag      sounds
			send     16
			pushi    #picture
			pushi    1
			pushi    910
			pushi    7
			pushi    1
			pushi    40
			pushi    142
			pushi    4
			pushi    0
			pushi    0
			pushi    320
			pushi    200
			pushi    379
			pushi    1
			lofsa    buttonCast
			push    
			pushi    148
			pushi    0
			pushi    #new
			pushi    0
			class    Plane
			send     4
			send     34
			sal      local0
			pushi    #plane
			pushi    1
			push    
			lofsa    buttonCast
			send     6
			pushi    #new
			pushi    1
			pushi    33
			class    IntArray
			send     6
			sat      temp0
			pushi    #new
			pushi    1
			pushi    12
			class    Str
			send     6
			sat      temp1
			pushi    4
			pushi    5
			lofsa    {SHIVERS}
			push    
			pushi    #data
			pushi    0
			lat      temp1
			send     4
			push    
			pushi    #data
			pushi    0
			lat      temp0
			send     4
			push    
			callk    SaveGame,  8
			sat      temp2
			pushi    #dispose
			pushi    0
			lat      temp0
			send     4
			pushi    #dispose
			pushi    0
			lat      temp1
			send     4
			lsg      global182
			ldi      0
			eq?     
			bnt      code_0157
			lst      temp2
			ldi      0
			gt?     
			bnt      code_0157
code_0157:
			lsg      global182
			ldi      0
			eq?     
			bnt      code_015e
code_015e:
			pushi    #init
			pushi    0
			lofsa    demoButton
			send     4
			pushi    #init
			pushi    0
			lofsa    quitButton
			send     4
			pushi    #init
			pushi    0
			super    Room,  4
			ret     
		)
	)
	
	(method (dispose)
		(local0 dispose:)
		(super dispose:)
	)
)

(instance newGameButton of Prop
	(properties
		x 75
		y 160
		view 910
		loop 3
	)
	
	(method (init)
		(super init: buttonCast)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(buttonCast delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(event localize: local0)
		(if
			(and
				(self onMe: event)
				(& (event type?) evMOUSEBUTTON)
				(user canControl:)
			)
			(event claimed: 1)
			(curRoom setScript: sNewGame)
		)
	)
)

(instance oldGameButton of Prop
	(properties
		x 180
		y 160
		view 910
		loop 4
	)
	
	(method (init)
		(super init: buttonCast)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(buttonCast delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(event localize: local0)
		(if
			(and
				(self onMe: event)
				(& (event type?) evMOUSEBUTTON)
				(user canControl:)
			)
			(event claimed: 1)
			(curRoom setScript: sOldGame)
		)
	)
)

(instance demoButton of Prop
	(properties
		x 75
		y 175
		view 910
		loop 2
	)
	
	(method (init)
		(super init: buttonCast)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(buttonCast delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(event localize: local0)
		(if
			(and
				(self onMe: event)
				(& (event type?) evMOUSEBUTTON)
				(user canControl:)
			)
			(event claimed: 1)
			(curRoom setScript: sRunDemo)
		)
	)
)

(instance creditsButton of Prop
	(properties
		x 180
		y 175
		view 910
		loop 5
	)
	
	(method (init)
		(super init: buttonCast)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(buttonCast delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(event localize: local0)
		(if
			(and
				(self onMe: event)
				(& (event type?) evMOUSEBUTTON)
				(user canControl:)
			)
			(event claimed: 1)
			(Prints {Credits not in yet})
		)
	)
)

(instance quitButton of Prop
	(properties
		x 145
		y 190
		view 910
		loop 1
	)
	
	(method (init)
		(super init: buttonCast)
		(mouseDownHandler add: self)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(buttonCast delete: self)
		(super dispose: &rest)
	)
	
	(method (handleEvent event &tmp [temp0 2])
		(event localize: local0)
		(if
			(and
				(self onMe: event)
				(& (event type?) evMOUSEBUTTON)
				(user canControl:)
			)
			(event claimed: 1)
			(curRoom setScript: sQuit)
		)
	)
)

(instance sNewGame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(newGameButton cel: 1)
				(UpdateScreenItem newGameButton)
				(sounds play: 15018 0 90 self)
			)
			(1
				(sounds fade: 20402 0 1 40 1 self)
			)
			(2
				(newGameButton cel: 0)
				(UpdateScreenItem newGameButton)
				(proc951_14)
				(theGame handsOn:)
				(curRoom newRoom: 922)
				(self dispose:)
			)
		)
	)
)

(instance sQuit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(quitButton cel: 1)
				(UpdateScreenItem quitButton)
				(sounds play: 15027 0 90 self)
			)
			(1
				(quitButton cel: 0)
				(UpdateScreenItem quitButton)
				(= cycles 1)
			)
			(2
				(if (== global182 1)
					(sounds fade: 20402 0 1 40 1 0)
					(sounds play: -30526 0 90 0)
					(local0 picture: 920)
					(newGameButton hide:)
					(quitButton hide:)
					(demoButton hide:)
					(UpdatePlane local0)
					(= seconds 10)
				else
					(= cycles 1)
				)
			)
			(3 (= quit 1) (self dispose:))
		)
	)
)

(instance sOldGame of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(oldGameButton cel: 1)
				(UpdateScreenItem oldGameButton)
				(sounds play: 15026 0 90 self)
			)
			(1
				(oldGameButton cel: 0)
				(UpdateScreenItem oldGameButton)
				(sounds fade: 20402 0 1 40 1 self)
			)
			(2
				(theGame handsOn:)
				(curRoom newRoom: 927)
				(self dispose:)
			)
		)
	)
)

(instance sCredits of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(creditsButton cel: 1)
				(UpdateScreenItem creditsButton)
				(sounds play: 15026 0 90 self)
			)
			(1
				(demoButton cel: 0)
				(UpdateScreenItem creditsButton)
				(sounds fade: 20402 0 1 40 1 self)
			)
			(2
				(theGame handsOn:)
				(curRoom newRoom: 903)
				(self dispose:)
			)
		)
	)
)

(instance sRunDemo of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theGame handsOff:)
				(demoButton cel: 1)
				(UpdateScreenItem demoButton)
				(sounds play: 15026 0 90 self)
			)
			(1
				(demoButton cel: 0)
				(UpdateScreenItem demoButton)
				(sounds fade: 20402 0 1 40 1 self)
			)
			(2
				(theGame handsOn:)
				(curRoom newRoom: 997)
				(self dispose:)
			)
		)
	)
)
