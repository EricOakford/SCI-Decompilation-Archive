;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64039)
(include sci.sh)
(use Main)
(use CueMe)
(use GenDialog)
(use String)
(use Print)
(use Game)
(use System)

(public
	oHandsOnWhenCued 0
)

(local
	local0
	local1
	local2
	local3
)
(class L7Room of Room
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
		oExtraPlanes 0
		oMainPlane 0
	)
	
	(method (init &tmp temp0)
		(if cuees (cuees release: dispose:) (= cuees 0))
		(if global202
			(= temp0
				(Str format: {Before init, room %hd} curRoomNum)
			)
			(Dummy (temp0 data?))
			(temp0 dispose:)
		)
		(super init: &rest)
		(Palette 1 999)
		(if (ResCheck 139 curRoomNum)
			(Palette 1 curRoomNum)
		else
			(Palette 1 600)
		)
		(Palette 2 0 235 100)
		(RemapColors 2 238 25)
		(RemapColors 2 237 45)
		(RemapColors 2 236 75)
		(if ((ScriptID 64017 0) test: 0)
			((ScriptID 90 0) dispose:)
		)
		(if ((ScriptID 64040 2) oPlane?)
			((ScriptID 64040 2) dispose:)
		)
	)
	
	(method (dispose &tmp temp0 temp1)
		(if oExtraPlanes
			(while (oExtraPlanes size:)
				(= temp0 (oExtraPlanes at: 0))
				(self deleteRoomPlane: temp0)
			)
			(oExtraPlanes dispose:)
			(= oExtraPlanes 0)
		)
		(proc64896_13)
		(messager kill:)
		(super dispose: &rest)
		(if global202
			(= temp1
				(Str format: {After dispose, room %hd} curRoomNum)
			)
			(Dummy (temp1 data?))
			(temp1 dispose:)
		)
	)
	
	(method (addRoomPlane param1 &tmp thePlane_2 planePriority)
		(if (or (not argc) (not param1))
			(Prints {improper call of addRoomPlane. tproom.sc djm})
			(return)
		)
		(= thePlane_2 plane)
		(= planePriority (plane priority?))
		(curRoom plane: param1)
		(if (not oExtraPlanes) (= oExtraPlanes (Set new:)))
		(oExtraPlanes addToEnd: param1)
		(if (not oMainPlane) (= oMainPlane thePlane_2))
		(thePlane_2 disable:)
		(param1 init: setPri: (++ planePriority))
	)
	
	(method (deleteRoomPlane param1)
		(if
			(or
				(not argc)
				(not param1)
				(not oExtraPlanes)
				(not oMainPlane)
			)
			(Prints {improper call of deleteRoomPlane. tproom.sc djm})
			(return)
		)
		(oExtraPlanes delete: param1)
		(if (== param1 plane)
			(if (oExtraPlanes size:)
				(= plane (List 8 (oExtraPlanes last:)))
			else
				(= plane oMainPlane)
				(= oMainPlane 0)
			)
			(plane enable:)
		)
		(param1 dispose:)
	)
	
	(method (exitRoom param1 param2 param3 param4)
		(if (< argc 3)
			(PrintDebug
				{You have to pass all three arguments.\ntheRoom, theX, and theY. SRC l7room.sc.}
			)
			(return)
		else
			(= local0 param1)
			(= local1 param2)
			(= local2 param3)
			(if (> argc 3) (= local3 param4) else (= local3 0))
			(self setScript: soExit)
		)
	)
	
	(method (gimme)
	)
)

(instance oHandsOnWhenCued of CueMe
	(properties)
	
	(method (cue)
		(theGame handsOn:)
	)
)

(instance soExit of Script
	(properties)
	
	(method (changeState newState)
		(switch (= state newState)
			(0
				(ego walkTo: local1 local2 self 1 0 1)
			)
			(1
				(theGame handsOff:)
				(if local3
					(= cycles 1)
				else
					(ego walkTo: local1 local2 self 0 0 0)
				)
			)
			(2
				(curRoom newRoom: local0)
				(self dispose:)
			)
		)
	)
)
