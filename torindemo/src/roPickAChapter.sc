;;; Sierra Script 1.0 - (do not remove this comment)
(script# 61100)
(include sci.sh)
(use Main)
(use n64011)
(use TPRoom)
(use TPScript)
(use GenDialg)
(use TranslucentPlane)
(use PushButton)
(use n64896)
(use Plane)
(use Array)
(use RandCyc)
(use Motion)
(use Actor)
(use System)

(public
	roPickAChapter 0
)

(instance oRoomChosen of Obj
	(properties)
	
	(method (doit param1)
		(Palette palSET_FROM_RESOURCE 999)
		(Palette palSET_FROM_RESOURCE 10000)
		(switch param1
			(2
				(= global202 1)
				(Palette palSET_FROM_RESOURCE 10000)
			)
			(3
				(= global202 2)
				(ego get: ((ScriptID 64001 1) get: 1))
				(ego get: ((ScriptID 64001 1) get: 0))
				(ego get: ((ScriptID 64001 1) get: 2))
				(ego get: ((ScriptID 64001 1) get: 5))
				(ego get: ((ScriptID 64001 0) get: 8))
				(ego get: ((ScriptID 64001 0) get: 9))
				(Palette palSET_FROM_RESOURCE 20000)
			)
			(4
				(= global202 3)
				(ego get: ((ScriptID 64001 1) get: 1))
				(ego get: ((ScriptID 64001 1) get: 0))
				(ego get: ((ScriptID 64001 1) get: 4))
				(ego get: ((ScriptID 64001 1) get: 2))
				(ego get: ((ScriptID 64001 1) get: 5))
				(ego get: ((ScriptID 64001 1) get: 3))
				(ego get: ((ScriptID 64001 0) get: 9))
				(ego get: ((ScriptID 64001 0) get: 31))
				(Palette palSET_FROM_RESOURCE 30000)
			)
			(5
				(= global202 4)
				(ego get: ((ScriptID 64001 1) get: 1))
				(ego get: ((ScriptID 64001 1) get: 0))
				(ego get: ((ScriptID 64001 1) get: 4))
				(ego get: ((ScriptID 64001 1) get: 2))
				(ego get: ((ScriptID 64001 1) get: 5))
				(ego get: ((ScriptID 64001 1) get: 3))
				(ego get: ((ScriptID 64001 1) get: 6))
				(ego get: ((ScriptID 64001 0) get: 9))
				(ego get: ((ScriptID 64001 0) get: 34))
				(Palette palSET_FROM_RESOURCE -25536)
			)
			(6
				(= global202 5)
				(ego get: ((ScriptID 64001 1) get: 1))
				(ego get: ((ScriptID 64001 1) get: 0))
				(ego get: ((ScriptID 64001 1) get: 4))
				(ego get: ((ScriptID 64001 1) get: 2))
				(ego get: ((ScriptID 64001 1) get: 5))
				(ego get: ((ScriptID 64001 1) get: 3))
				(ego get: ((ScriptID 64001 1) get: 6))
				(ego get: ((ScriptID 64001 0) get: 9))
				(ego get: ((ScriptID 64001 0) get: 35))
				(ego get: ((ScriptID 64001 0) get: 34))
				(Palette palSET_FROM_RESOURCE -15536)
			)
			(9 (theGame deleteVerbHandler:))
			(10 (theGame restore:))
			(8 (= quit 1) (return))
		)
		(if (or global242 global243)
			(= global242 0)
			(curRoom newRoom: (proc64011_0 global202))
		else
			(switch param1
				(1 (curRoom newRoom: 8000))
				(2 (curRoom newRoom: 10000))
				(3 (curRoom newRoom: 20000))
				(4 (curRoom newRoom: 30000))
				(5 (curRoom newRoom: -25536))
				(6 (curRoom newRoom: -15536))
				(7 (curRoom newRoom: -8536))
				(else  (= quit 1))
			)
		)
	)
)

(instance oIntroButton of MessageButton
	(properties
		noun 1
		x 480
		y 96
		ioMine 69
		curSlot 1
		font 2510
		compress 130
		findNearestOpen -5523
		updateItemSlot -5522
		testHotspotVerb 200
	)
	
	(method (init)
		(= back gBack)
		(= fore gFore)
		(super init: &rest)
	)
)

(instance oResumeButton of MessageButton
	(properties
		noun 9
		x 252
		y 440
		ioMine 69
		curSlot 9
		font 2510
		compress 130
		findNearestOpen -5523
		updateItemSlot -5522
		testHotspotVerb 200
	)
	
	(method (init)
		(= back gBack)
		(= fore gFore)
		(super init: &rest)
	)
)

(instance oRestoreButton of MessageButton
	(properties
		noun 10
		x 100
		y 440
		ioMine 69
		curSlot 10
		font 2510
		compress 130
		findNearestOpen -5523
		updateItemSlot -5522
		testHotspotVerb 200
	)
	
	(method (init)
		(= back gBack)
		(= fore gFore)
		(super init: &rest)
	)
)

(instance oChapter1Button of MessageButton
	(properties
		noun 2
		x 480
		y 132
		ioMine 69
		curSlot 2
		font 2510
		compress 130
		findNearestOpen -5523
		updateItemSlot -5522
		testHotspotVerb 200
	)
	
	(method (init)
		(= back gBack)
		(= fore gFore)
		(super init: &rest)
		(AddLine
			oPickAChapter
			434
			(+ y 14)
			x
			(+ y 14)
			200
			back
			0
			0
			2
		)
	)
)

(instance oChapter2Button of MessageButton
	(properties
		noun 3
		x 480
		y 168
		ioMine 69
		curSlot 3
		font 2510
		compress 130
		findNearestOpen -5523
		updateItemSlot -5522
		testHotspotVerb 200
	)
	
	(method (init)
		(= back gBack)
		(= fore gFore)
		(super init: &rest)
		(AddLine
			oPickAChapter
			413
			(+ y 14)
			x
			(+ y 14)
			200
			back
			0
			0
			2
		)
	)
)

(instance oChapter3Button of MessageButton
	(properties
		noun 4
		x 480
		y 204
		ioMine 69
		curSlot 4
		font 2510
		compress 130
		findNearestOpen -5523
		updateItemSlot -5522
		testHotspotVerb 200
	)
	
	(method (init)
		(= back gBack)
		(= fore gFore)
		(super init: &rest)
		(AddLine
			oPickAChapter
			389
			(+ y 14)
			x
			(+ y 14)
			200
			back
			0
			0
			2
		)
	)
)

(instance oChapter4Button of MessageButton
	(properties
		noun 5
		x 480
		y 240
		ioMine 69
		curSlot 5
		font 2510
		compress 130
		findNearestOpen -5523
		updateItemSlot -5522
		testHotspotVerb 200
	)
	
	(method (init)
		(= back gBack)
		(= fore gFore)
		(super init: &rest)
		(AddLine
			oPickAChapter
			346
			(+ y 14)
			x
			(+ y 14)
			200
			back
			0
			0
			2
		)
	)
)

(instance oChapter5Button of MessageButton
	(properties
		noun 6
		x 480
		y 276
		ioMine 69
		curSlot 6
		font 2510
		compress 130
		findNearestOpen -5523
		updateItemSlot -5522
		testHotspotVerb 200
	)
	
	(method (init)
		(= back gBack)
		(= fore gFore)
		(super init: &rest)
		(AddLine
			oPickAChapter
			275
			(+ y 14)
			x
			(+ y 14)
			200
			back
			0
			0
			2
		)
	)
)

(instance oEpilogueButton of MessageButton
	(properties
		noun 7
		x 480
		y 312
		ioMine 69
		curSlot 7
		font 2510
		compress 130
		findNearestOpen -5523
		updateItemSlot -5522
		testHotspotVerb 200
	)
	
	(method (init)
		(= back gBack)
		(= fore gFore)
		(super init: &rest)
	)
)

(instance oExitButton of MessageButton
	(properties
		noun 8
		x 480
		y 440
		ioMine 69
		curSlot 8
		font 2510
		compress 130
		findNearestOpen -5523
		updateItemSlot -5522
		testHotspotVerb 200
	)
	
	(method (init)
		(= back gBack)
		(= fore gFore)
		(super init: &rest)
	)
)

(instance soDemoIntro of TPScript
	(properties)
	
	(method (changeState newState &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8)
		(switch (= state newState)
			(0
				(music1 nHeight: -5521)
				(music1 lThumbLoop: -5521)
				(= ticks 490)
			)
			(1
				(if (= temp0 (MakeMessageText 0 0 1 1 300))
					(= temp1 (IntArray new: 4))
					(KText 0 (temp1 data?) (temp0 data?) global268 350 1)
					(= temp2 (+ (temp1 at: 2) 1))
					(= temp3 (+ (temp1 at: 3) 1))
					(= temp4 (+ temp2 16))
					(= temp5 (- (+ temp3 16) 1))
					(temp1 dispose:)
					(= temp6 (/ (- screenWidth temp4) 2))
					(= temp7 350)
					(oTransPlane
						init: temp6 temp7 (- (+ temp6 temp4) 1) (- (+ temp7 temp5) 1)
					)
					(UpdatePlane oTransPlane)
					(= temp8 (Bitmap 0 temp4 temp5 255 255 640 480))
					(Bitmap 5 temp8 0 0 (- temp4 1) (- temp5 1) 255)
					(Bitmap
						4
						temp8
						(temp0 data?)
						0
						0
						(- temp2 1)
						(- temp3 1)
						235
						255
						255
						global268
						0
						255
						0
					)
					(voText bitmap: temp8 init: oTransPlane posn: 8 8)
					(UpdateScreenItem voText)
					(temp0 dispose:)
				)
				(FrameOut)
				(= ticks 400)
			)
			(2
				(= global202 2)
				(curRoom newRoom: 20100)
				(self dispose:)
			)
		)
	)
)

(instance oTwinkle1 of Prop
	(properties
		x 2
		y 313
		view -4436
	)
)

(instance oTwinkle2 of Prop
	(properties
		x 609
		y 279
		view -4436
		loop 1
	)
)

(instance oTwinkle3 of Prop
	(properties
		x 30
		y 30
		view -4436
		loop 2
	)
)

(instance oTwinkle4 of Prop
	(properties
		x 516
		y 50
		view -4436
		loop 3
	)
)

(instance oTwinkle5 of Prop
	(properties
		x 464
		y 196
		view -4436
	)
)

(instance oTwinkle6 of Prop
	(properties
		x 420
		y 429
		view -4436
		loop 2
	)
)

(instance oTwinkle7 of Prop
	(properties
		x 122
		y 394
		view -4436
		loop 3
	)
)

(instance oTransPlane of TranslucentPlane
	(properties
		priority 1100
	)
)

(instance voText of View
	(properties)
)

(instance voLogo of View
	(properties
		x 48
		y 231
		view -5521
	)
)

(instance oPickAChapter of Plane
	(properties)
)

(instance roPickAChapter of TPRoom
	(properties
		picture -4436
	)
	
	(method (init &tmp temp0)
		(Palette palSET_FROM_RESOURCE 999)
		(oPickAChapter
			priority: (GetHighPlanePri)
			init: 0 0 639 479
		)
		(= plane oPickAChapter)
		(super init: &rest)
		(voLogo init: oPickAChapter)
		(Palette palSET_FROM_RESOURCE -5521)
		(if (not global242) (curRoom setScript: soDemoIntro))
		(= global202 0)
		(oIntroButton removeItem: oRoomChosen init: oPickAChapter)
		(oRestoreButton
			removeItem: oRoomChosen
			init: oPickAChapter
		)
		(oChapter1Button
			removeItem: oRoomChosen
			init: oPickAChapter
		)
		(oChapter2Button
			removeItem: oRoomChosen
			init: oPickAChapter
		)
		(oChapter3Button
			removeItem: oRoomChosen
			init: oPickAChapter
		)
		(oChapter4Button
			removeItem: oRoomChosen
			init: oPickAChapter
		)
		(oChapter5Button
			removeItem: oRoomChosen
			init: oPickAChapter
		)
		(oEpilogueButton
			removeItem: oRoomChosen
			init: oPickAChapter
		)
		(oExitButton removeItem: oRoomChosen init: oPickAChapter)
		(if
			(or
				(!= (= temp0 (FileIO fiOPEN {torinsg.cat})) -1)
				(SaveGame 3 {Autosave} 0 (KString 9 version))
			)
			(FileIO fiCLOSE temp0)
			(oResumeButton
				removeItem: oRoomChosen
				init: oPickAChapter
			)
		)
		(oTwinkle1 init: cycleSpeed: (Random 7 17) setCycle: Fwd)
		(oTwinkle2
			init:
			cycleSpeed: (Random 7 17)
			setCycle: RandCycle
		)
		(oTwinkle3
			init:
			cycleSpeed: (Random 7 17)
			setCycle: RandCycle
		)
		(oTwinkle4
			init:
			cycleSpeed: (Random 7 17)
			setCycle: RandCycle
		)
		(oTwinkle5 init: cycleSpeed: (Random 7 17) setCycle: Fwd)
		(oTwinkle6
			init:
			cycleSpeed: (Random 7 17)
			setCycle: RandCycle
		)
		(oTwinkle7
			init:
			cycleSpeed: (Random 7 17)
			setCycle: RandCycle
		)
		(proc64896_1 0 1 0 1)
	)
	
	(method (dispose)
		(if (oTransPlane nScreenSizeX?) (oTransPlane dispose:))
		(super dispose:)
	)
)
