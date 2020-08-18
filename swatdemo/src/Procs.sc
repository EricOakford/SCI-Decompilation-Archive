;;; Sierra Script 1.0 - (do not remove this comment)
(script# PROCS)
(include game.sh)
(use Main)
(use WalkTalk)
(use Actor)
(use System)

(public
	Face 0
	proc902_1 1
	ShowRoomExit 2
	proc902_3 3
	StopRobot 4
	PlayRobot 5
)

(local
	[local0 2]
)
(procedure (Face actor1 actor2 both whoToCue &tmp ang1To2 theX theY obj)
	(= obj 0)
	(if (not (> argc 3))
		(= theX (actor2 x?))
		(= theY (actor2 y?))
		(if (== argc 3)
			(= obj both)
		)
	else
		(= theX actor2)
		(= theY both)
		(if (== argc 4)
			(= obj whoToCue)
		)
	)
	(= ang1To2
		(GetAngle (actor1 x?) (actor1 y?) theX theY)
	)
	(actor1 setHeading: ang1To2 obj)
)

(procedure (proc902_1 &tmp [temp0 19])
)

(procedure (ShowRoomExit n x y &tmp theButton theX theY)
	(switch argc
		(0
			(if (curRoom inset?)
				(= theX 15)
				(= theY 10)
			)
		)
		(1
			(= theX 15)
			(= theY 10)
		)
		(2
			(= theX x)
			(= theY 10)
		)
		(3
			(= theX x)
			(= theY y)
		)
	)
	((= theButton (ExitButton new:))
		view: 984
		loop: 0
		cel: 0
		posn: theX theY
		roomNum: n
		disposeNow: FALSE
		code: 0
		init:
	)
)

(procedure (proc902_3 &tmp [temp0 2])
)

(procedure (StopRobot doICue who &tmp temp0 theRobot myObj)
	(if (and argc doICue)
		(= theRobot doICue)
	else
		(= theRobot 0)
	)
	(if (> argc 1)
		(= myObj who)
	else
		(= myObj ego)
	)
	(WalkieTalkie killRobot: theRobot myObj)
)

(procedure (PlayRobot which anX anY who pri hLC &tmp temp0 myX myY)
	(if (> argc 1)
		(= myX anX)
	else
		(= myX 0)
	)
	(if (> argc 2)
		(= myY anY)
	else
		(= myY 0)
	)
	(WalkieTalkie
		doRobot: which myX myY
			(if (not (> argc 7)) self else 0)
			(if (> argc 3) who else ego)
			(if (> argc 4) pri else -1)
			(if (> argc 5) hLC else 0)
	)
	(= activeRobot which)
)

(instance doUpdateCode of Code
	
	(method (doit obj)
		(if (not (& (obj signal?) viewHidden))
			(UpdateScreenItem obj)
		)
	)
)

(class ExitButton of View
	(properties
		x 15
		y 10
		z 0
		view 989
		disposeNow 0
		roomNum 0
		code 0
	)
	
	(method (init)
		(super init: &rest)
		(mouseDownHandler addToFront: self)
	)
	
	(method (doit)
		(super doit: &rest)
		(if (and disposeNow (curRoom inset?))
			(curRoom newRoom: roomNum)
		)
	)
	
	(method (dispose)
		(mouseDownHandler delete: self)
		(super dispose:)
	)
	
	(method (handleEvent event &tmp evt temp1)
		(if
			(or
				(not (self onMe: event))
				(not (event type?))
				(not (user input?))
			)
			(super handleEvent: event)
			(return)
		)
		(= temp1 0)
		(repeat
			((= evt ((user curEvent?) new:)) localize: thePlane)
			(if (== (evt type?) mouseUp) (break))
			(if (self onMe: evt)
				(= temp1 (= cel 1))
				(UpdateScreenItem self)
			else
				(= temp1 (= cel 0))
				(UpdateScreenItem self)
			)
			(FrameOut)
			(evt dispose:)
		)
		(evt dispose:)
		(= cel 0)
		(UpdateScreenItem self)
		(FrameOut)
		(if temp1
			(event claimed: 1)
			(self doVerb:)
		else
			(super handleEvent: event)
		)
	)
	
	(method (doVerb)
		(if code
			(code doit:)
		else
			(= disposeNow TRUE)
		)
	)
)
