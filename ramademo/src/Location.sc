;;; Sierra Script 1.0 - (do not remove this comment)
(script# 20)
(include sci.sh)
(use Main)
(use Procs)
(use Plane)
(use Game)
(use User)
(use Actor)
(use System)

(public
	Location 0
)

(procedure (localproc_0ae1 param1 param2 &tmp temp0 temp1)
	(return
		(if (and param1 param2)
			(= temp0 (param1 heading?))
			(if (< (= temp1 (param2 heading?)) temp0)
				(= temp1 (+ temp1 360))
			)
			(return (- temp1 temp0))
		else
			(return 0)
		)
	)
)

(class Location of Room
	(properties
		scratch 0
		script 0
		number 0
		modNum -1
		noun 0
		case 0
		timer 0
		keep 0
		initialized 0
		picture -1
		plane 0
		style $ffff
		exitStyle -1
		horizon 0
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		purge 500
		picAngle 0
		vanishingX 160
		vanishingY 0
		obstacles 0
		inset 0
		edgeN 40
		edgeE 319
		edgeW 0
		edgeS 189
		heading 0
		picObj 0
		exitNE 0
		exitN 0
		exitNW 0
	)
	
	(method (init)
		((= plane (= gTopPlane topPlane))
			init: 30 39 624 331
			priority: 7
			addCast: roomCast
		)
		(= gRoomCast roomCast)
		(cast add: roomCast)
		(if picObj
			(self
				picture: (picObj picture?)
				heading: (picObj heading?)
			)
		)
		(super init: &rest)
		(mouseDownHandler add: self)
		(keyDownHandler add: self)
		(gTopPlane addExtMH: self)
		(tempPlane init: 0 0 640 480)
	)
	
	(method (dispose)
		(if picObj (picObj dispose:) (= picObj 0))
		(cast delete: roomCast)
		(gTopPlane deleteExtMH: self)
		(mouseDownHandler delete: self)
		(keyDownHandler delete: self)
		(super dispose:)
		(tempPlane dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((not (User controls?)) 0)
			((or (proc1111_20 4) (proc1111_20 1)) 0)
			(
				(and
					(not (event type?))
					(not (theCursor isInvCursor:))
					(plane onMe: event)
				)
				(event claimed: 1 localize: plane)
				(cond 
					((and edgeW (< (event x?) edgeW))
						(if (< (localproc_0ae1 (picObj left:) picObj) 91)
							(theCursor setDirCursor: leftCursor)
						else
							(theCursor setDirCursor: left180Cursor)
						)
					)
					((and edgeE (> (event x?) edgeE))
						(if (< (localproc_0ae1 picObj (picObj right:)) 91)
							(theCursor setDirCursor: rightCursor)
						else
							(theCursor setDirCursor: right180Cursor)
						)
					)
					((and edgeN (< (event y?) edgeN)) (theCursor setDirCursor: upCursor))
					((and edgeS (> (event y?) edgeS))
						(if (curRoom isKindOf: CloseupLocation)
							(theCursor setDirCursor: backupCursor)
						else
							(theCursor setDirCursor: downCursor)
						)
					)
					(else (event claimed: 0 globalize:))
				)
			)
			((& (event type?) evMOUSEBUTTON)
				(if
				(and (theCursor isDirCursor:) (plane onMe: event))
					(event claimed: 1 localize: plane)
					(switch (theCursor cel?)
						(1 (self yaw: 1))
						(1 (self yaw: 1))
						(0 (self yaw: -1))
						(0 (self yaw: -1))
						(7 (self pitch: -1))
						(8 (self pitch: 1))
						(5 (self pitch: 1))
						(else 
							(event claimed: 0 globalize:)
						)
					)
				)
			)
			((& (event type?) evMENUSTART)
				(event claimed: 1)
				(switch (event message?)
					(JOY_UP (self forward: 1))
					(JOY_UPRIGHT (self forward: 2))
					(JOY_RIGHT (self yaw: 1))
					(JOY_DOWN
						(self newRoom: prevRoomNum)
					)
					(JOY_LEFT (self yaw: -1))
					(JOY_UPLEFT (self forward: 8))
					(else  (event claimed: 0))
				)
			)
			((& (event type?) evKEYBOARD)
				(event claimed: 1)
				(switch (event message?)
					(KEY_u (self pitch: -1))
					(KEY_U (self pitch: -1))
					(KEY_d (self pitch: 1))
					(KEY_D (self pitch: 1))
					(else  (event claimed: 0))
				)
			)
		)
	)
	
	(method (doVerb)
	)
	
	(method (newRoom)
		(if (and (!= exitStyle -1) (& exitStyle $0100))
			(= exitStyle (& exitStyle $00ff))
			((curRoom plane?) drawPic: 1 exitStyle)
			(UpdatePlane (curRoom plane?))
			(FrameOut)
			(= exitStyle -1)
		)
		(super newRoom: &rest)
	)
	
	(method (addPicObj param1 &tmp temp0 temp1 temp2)
		(if (== picObj 0) (self setPicObj: [param1 0]))
		(= temp1 0)
		(= temp2 0)
		(while (< temp2 argc)
			((= temp0 [param1 temp2]) left: temp1)
			(if (!= temp2 0) (temp1 right: temp0))
			(= temp1 temp0)
			(++ temp2)
		)
	)
	
	(method (setPicObj param1)
		(if picObj (picObj dispose:))
		(if argc
			(self
				picObj: param1
				heading: (param1 heading?)
				edgeN: (param1 edgeN?)
				edgeS: (param1 edgeS?)
				edgeE: (param1 edgeE?)
				edgeW: (param1 edgeW?)
			)
			(param1 init:)
			(if global100 ((ScriptID 10 0) init:))
		)
	)
	
	(method (pitch param1)
		(switch param1
			(1
				(if (picObj down?) (self setPicObj: (picObj down?)))
			)
			(-1
				(if (picObj up?) (self setPicObj: (picObj up?)))
			)
		)
	)
	
	(method (yaw param1)
		(switch param1
			(1
				(if (picObj right:) (self setPicObj: (picObj right:)))
			)
			(-1
				(if (picObj left:) (self setPicObj: (picObj left:)))
			)
		)
	)
	
	(method (forward param1)
		(switch param1
			(1
				(if exitN (exitN doVerb: 9))
			)
			(2
				(if exitNE (exitNE doVerb: 9))
			)
			(8
				(if exitNW (exitNW doVerb: 9))
			)
		)
	)
)

(class CameraAngle of Object
	(properties
		scratch 0
		picture 0
		left 0
		right 0
		up 0
		down 0
		heading 0
		edgeN 32
		edgeS 260
		edgeE 560
		edgeW 32
	)
	
	(method (init)
		(curRoom drawPic: picture)
	)
	
	(method (dispose)
		(curRoom exitN: 0 exitNE: 0 exitNW: 0)
		(roomCast dispose:)
		(gTopPlane addCast: roomCast)
		((gTopPlane theFtrs?) eachElementDo: #dispose release:)
		(super dispose:)
	)
)

(class CloseupLocation of Location
	(properties
		scratch 0
		script 0
		number 0
		modNum -1
		noun 0
		case 0
		timer 0
		keep 0
		initialized 0
		picture -1
		plane 0
		style $ffff
		exitStyle -1
		horizon 0
		controls 0
		north 0
		east 0
		south 0
		west 0
		curPic 0
		purge 500
		picAngle 0
		vanishingX 160
		vanishingY 0
		obstacles 0
		inset 0
		edgeN 40
		edgeE 319
		edgeW 0
		edgeS 189
		heading 0
		picObj 0
		exitNE 0
		exitN 0
		exitNW 0
	)
	
	(method (init param1)
		(super init:)
		(closeCameraAngle picture: param1)
		(closeCameraAngle heading: (self heading?))
		(self setPicObj: closeCameraAngle)
	)
	
	(method (pitch)
		(= global140 0)
		(self newRoom: prevRoomNum)
	)
	
	(method (yaw param1)
		(switch param1
			(1 (= global140 270))
			(-1 (= global140 90))
		)
		(self newRoom: prevRoomNum)
	)
)

(instance closeCameraAngle of CameraAngle
	(properties
		edgeN 0
	)
)

(instance topPlane of Plane
	(properties)
)

(instance roomCast of Cast
	(properties)
)

(instance rightCursor of View
	(properties
		view 600
		cel 1
	)
)

(instance leftCursor of View
	(properties
		view 600
	)
)

(instance left180Cursor of View
	(properties
		view 600
	)
)

(instance right180Cursor of View
	(properties
		view 600
		cel 1
	)
)

(instance upCursor of View
	(properties
		view 600
		cel 7
	)
)

(instance downCursor of View
	(properties
		view 600
		cel 8
	)
)

(instance backupCursor of View
	(properties
		view 600
		cel 5
	)
)

(instance tempPlane of Plane
	(properties
		picture 60
		priority 6
	)
)
