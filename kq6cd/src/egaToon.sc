;;; Sierra Script 1.0 - (do not remove this comment)
(script# 106)
(include sci.sh)
(use Main)
(use KQ6Room)
(use System)

(public
	egaToon 0
)

(local
	[local0 400]
	local400
	local401
	local402 =  1
	local403
	[local404 4]
	local408
)
(instance egaToon of KQ6Room
	(properties
		picture 107
		autoLoad 0
	)
	
	(method (init)
		(theGame handsOff:)
		(Cursor showCursor: 0)
		(theIconBar disable: height: -100 activateHeight: -100)
		(self setScript: openingScript)
		(openingScript setScript: playMusic)
		(super init: &rest)
		(directionHandler addToFront: self)
		(keyDownHandler addToFront: self)
		(mouseDownHandler addToFront: self)
		(Palette palSET_FROM_RESOURCE 999)
	)
	
	(method (handleEvent)
		(Cursor showCursor: 1)
		(theGame restart: 1)
	)
	
	(method (newRoom)
		(theIconBar disable: height: 0 activateHeight: 0)
		(Cursor showCursor: 1)
		(directionHandler delete: self)
		(keyDownHandler delete: self)
		(mouseDownHandler delete: self)
		(super newRoom: &rest)
	)
)

(instance findSize of Code
	(properties)
	
	(method (doit param1)
		(TextSize @local404 param1 3110 315)
		(return (/ (- 180 (- [local404 2] [local404 0])) 2))
	)
)

(instance openingScript of Script
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2)
		(switch (= state newState)
			(0 (= cycles 15))
			(1
				(Display 106 0 108 local400)
				(= temp1 (Message msgNEXT 0))
				(if local403
					(Message msgGET 105 2 0 2 local402 @local0)
					(= local408 (Message msgSIZE 105 2 0 2 local402))
				else
					(Message msgGET 105 2 0 0 local402 @local0)
					(= local408 (Message msgSIZE 105 2 0 0 local402))
				)
				(= temp2 (findSize doit: @local0))
				(switch temp1
					(12
						(= local400
							(Display
								@local0
								dsCOORD
								12
								temp2
								dsWIDTH
								300
								dsCOLOR
								6
								dsFONT
								3110
								dsALIGN
								1
								dsSAVEPIXELS
							)
						)
					)
					(2
						(= local400
							(Display
								@local0
								dsCOORD
								12
								temp2
								dsWIDTH
								300
								dsCOLOR
								20
								dsFONT
								3110
								dsALIGN
								1
								dsSAVEPIXELS
							)
						)
					)
					(28
						(= local400
							(Display
								@local0
								dsCOORD
								12
								temp2
								dsWIDTH
								300
								dsCOLOR
								30
								dsFONT
								3110
								dsALIGN
								1
								dsSAVEPIXELS
							)
						)
					)
					(else 
						(= local400
							(Display
								@local0
								dsCOORD
								12
								temp2
								dsWIDTH
								300
								dsCOLOR
								40
								dsFONT
								3110
								dsALIGN
								1
								dsSAVEPIXELS
							)
						)
					)
				)
				(= cycles 1)
			)
			(2
				(switch local403
					(0
						(if (OneOf local402 17 19 26) (playMusic cue:))
					)
					(1
						(if (OneOf local402 3 9) (playMusic cue:))
					)
				)
				(= cycles 1)
			)
			(3
				(if (< local408 15)
					(= seconds 5)
				else
					(= seconds (/ local408 8))
				)
			)
			(4
				(cond 
					(
					(and (== (= temp0 (++ local402)) 12) local403) (Display 106 0 108 local400) (= cycles 2))
					((and (not local403) (== temp0 28)) (= local403 1) (= local402 1) (self init:))
					(else (self init:))
				)
			)
			(5
				(self setScript: (ScriptID 107 0) self)
			)
			(6 (curRoom newRoom: 200))
		)
	)
)

(instance playMusic of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(theMusic loop: -1 number: 105 play:)
			)
			(1 (theMusic number: 106 play:))
			(2 (theMusic number: 107 play:))
			(3
				(theMusic loop: 1 number: 108 play:)
			)
			(4
				(theMusic stop: number: 109 loop: -1 play:)
			)
			(5
				(theMusic stop: number: 110 loop: 1 play:)
			)
		)
	)
)
