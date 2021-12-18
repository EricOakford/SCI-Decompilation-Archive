;;; Sierra Script 1.0 - (do not remove this comment)
(script# 910)
(include sci.sh)
(use Main)
(use Procs)
(use Plane)
(use String)
(use Array)
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
		(theGame handsOn:)
		(gSound1 pause: 0)
		(gSound2 pause: 0)
		(gSound3 pause: 0)
		(gSound4 pause: 0)
		(gSound5 pause: 0)
		(gSound6 pause: 0)
		(sounds stopAll:)
		(proc951_7 20402)
		(proc951_9 20402)
		(sounds play: 20402 -1 0 0)
		(sounds fade: 20402 90 1 40 0 0)
		(= local0
			((Plane new:)
				picture: 910
				priority: 40
				init: 0 0 320 200
				addCast: buttonCast
				yourself:
			)
		)
		(buttonCast plane: local0)
		(= temp0 (IntArray new: 21))
		(= temp1 (Str new: 12))
		(= temp2
			(SaveGame 5 {SHIVER} (temp1 data?) (temp0 data?))
		)
		(temp0 dispose:)
		(temp1 dispose:)
		(newGameButton init:)
		(if (and (== global186 0) (> temp2 0))
			(oldGameButton init:)
		)
		(if (== global186 0) (creditsButton init:))
		(demoButton init:)
		(quitButton init:)
		(super init:)
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
			(curRoom setScript: sCredits)
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
				(sounds play: 15018 0 127 self)
			)
			(1
				(sounds fade: 20402 0 1 40 1 self)
			)
			(2
				(newGameButton cel: 0)
				(UpdateScreenItem newGameButton)
				(proc951_14)
				(theGame handsOn:)
				(if (== global186 1)
					(curRoom newRoom: 930)
				else
					(curRoom newRoom: 922)
				)
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
				(sounds play: 15027 0 127 self)
			)
			(1
				(quitButton cel: 0)
				(UpdateScreenItem quitButton)
				(= cycles 1)
			)
			(2
				(if (== global186 1)
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
				(sounds play: 15034 0 127 self)
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
				(sounds play: 15036 0 127 self)
			)
			(1
				(creditsButton cel: 0)
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
				(sounds play: 15026 0 127 self)
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
