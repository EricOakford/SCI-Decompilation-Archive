;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64037)
(include sci.sh)
(use Main)
(use PopMenu)
(use TranslucentPlane)
(use ModalPlane)
(use PushButton)
(use InventItem)
(use GenDialog)
(use Actor)
(use System)

(public
	oInvHandler 0
	proc64037_1 1
	oInvPlane 2
)

(procedure (proc64037_1 &tmp temp0 temp1 temp2 temp3 temp4 newView newTextItem)
	(= temp1
		(+
			(*
				((ScriptID 64037 0) viewSlotsX?)
				(+ ((ScriptID 64037 0) slotIncX?) 3)
			)
			3
		)
	)
	(= temp0
		(+
			(= temp0
				(+
					(*
						((ScriptID 64037 0) viewSlotsY?)
						(+ ((ScriptID 64037 0) slotIncY?) 3)
					)
					3
				)
			)
			15
		)
	)
	(= temp2 (/ (- 640 temp1) 2))
	(= temp3 (Max (/ (- 400 temp0) 2) 0))
	(oInvPlane
		init: temp2 temp3 (+ temp2 (- temp1 1)) (+ temp3 (- temp0 1))
	)
	(= newView (View new:))
	(newView bitmap: (Bitmap 0 temp1 15 255 82 640 480 0))
	(Bitmap 5 (newView bitmap?) 0 0 (- temp1 1) 14 82)
	(newView setPri: 10 init: oInvPlane)
	(= temp4 (MakeMessageText 0 0 50 1 14))
	(= newTextItem (TextItem new:))
	(newTextItem
		text: temp4
		font: 0
		fore: 0
		back: 255
		skip: 255
		nMinWidth: temp1
		x: 0
		y: 0
		priority: 15
		fixPriority: 1
		init: oInvPlane
	)
	(oInvClose x: 2 y: 2 setPri: 20 init: oInvPlane)
	((OpaqueFeature new:) init: oInvPlane)
	((MoveFeature new:)
		init: oInvPlane
		y: 15
		makeTopBorder: 15
	)
	((ScriptID 64037 0) init: (oInvPlane getMainCast:))
)

(instance oInvPlane of TranslucentPlane
	(properties)
	
	(method (setPri)
		(super setPri: &rest)
		(if (and (== curRoomNum 110) (!= priority -1))
			(curRoom notify: 4)
		)
	)
)

(instance oInvClose of PushButton
	(properties
		view -5236
		signal $4021
	)
	
	(method (doSelect)
		((ScriptID 64037 2) setPri: -1)
	)
)

(instance oInvHandler of InvHandler
	(properties
		viewSlotsX 7
		viewSlotsY 5
		invSlotsX 7
		invSlotsY 5
		screenLocX 3
		screenLocY 81
		slotIncX 51
		slotIncY 67
		blankID -5237
	)
	
	(method (initItems &tmp [temp0 3])
		(self
			add:
				0
				ioViceGrips
				1
				ioNeedle
				2
				ioLockpick
				3
				ioCruiseTicket
				4
				ioScorecard
				5
				ioKeyCard
				6
				ioLewdPhoto
				7
				ioMucilage
				8
				ioStickyPhoto
				9
				ioPhotoID
				10
				ioPassport
				11
				ioMasterKey
				12
				ioSuitcase
				13
				ioPrudeAndProud
				14
				ioBookJacket
				15
				ioEroticBook
				16
				ioEroticAndProud
				17
				ioFirehose
				18
				ioLubricant
				19
				ioDeodorant
				20
				ioHeatBulb
				21
				ioChaseLights
				22
				ioRemote
				23
				ioHorseshoe
				24
				ioToiletPaper
				25
				ioSouvenirDice
				26
				ioShavedDice
				27
				ioLegalDice
				28
				ioMoney
				29
				ioMagPage
				30
				ioPot
				31
				ioSalt
				32
				ioBeaverMilk
				33
				ioLimeJuice
				34
				ioMold
				35
				ioBeaverCheese
				36
				ioKumquat
				37
				ioQuiche
				38
				ioOrgasmPowder
				39
				ioQuichePlus
				40
				ioScrewdriver
				41
				ioJumperWire
				42
				ioKnife
				43
				ioPolyester
				44
				ioKZJelly
				45
				ioHanky
				46
				ioHankyLubed
				47
				ioBowlingBall
				48
				ioInsurance
				49
				ioStock
				50
				ioHairWeaveKit
		)
	)
)

(instance ioViceGrips of InventItem
	(properties
		verb 13
		noun 48
		vInventory -5335
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(super doVerb: theVerb)
				(curRoom notify: 5)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ioNeedle of InventItem
	(properties
		verb 14
		noun 31
		vInventory -5334
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(super doVerb: theVerb)
				(curRoom notify: 5)
			)
			(13
				(ego put: self)
				(ego get: ioLockpick)
				(curRoom notify: 8)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ioLockpick of InventItem
	(properties
		verb 65
		noun 30
		vInventory -5333
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioCruiseTicket of InventItem
	(properties
		verb 15
		noun 10
		vInventory -5332
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioScorecard of InventItem
	(properties
		verb 16
		noun 40
		vInventory -5331
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioKeyCard of InventItem
	(properties
		verb 17
		noun 21
		vInventory -5330
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioLewdPhoto of InventItem
	(properties
		verb 18
		noun 24
		vInventory -5329
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioMucilage of InventItem
	(properties
		verb 19
		noun 29
		vInventory -5328
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioStickyPhoto of InventItem
	(properties
		verb 20
		noun 43
		vInventory -5327
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioPhotoID of InventItem
	(properties
		verb 21
		noun 34
		vInventory -5326
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioPassport of InventItem
	(properties
		verb 22
		noun 33
		vInventory -5325
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioMasterKey of InventItem
	(properties
		verb 23
		noun 27
		vInventory -5324
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioSuitcase of InventItem
	(properties
		verb 24
		noun 45
		vInventory -5323
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioPrudeAndProud of InventItem
	(properties
		verb 26
		noun 6
		vInventory -5322
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
		(self addHotspotVerb: 201)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(201
				(ego put: self)
				(ego get: ioBookJacket)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ioBookJacket of InventItem
	(properties
		verb 27
		noun 5
		vInventory -5321
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioEroticBook of InventItem
	(properties
		verb 28
		noun 4
		vInventory -5320
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(27
				(ego put: self)
				(ego put: ioBookJacket)
				(ego get: ioEroticAndProud)
				((ScriptID 64017 0) set: 3)
			)
			(else  (super doVerb: theVerb))
		)
	)
)

(instance ioEroticAndProud of InventItem
	(properties
		verb 29
		noun 3
		vInventory -5319
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioFirehose of InventItem
	(properties
		verb 30
		noun 15
		vInventory -5318
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioLubricant of InventItem
	(properties
		verb 31
		noun 42
		vInventory -5317
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioDeodorant of InventItem
	(properties
		verb 32
		noun 11
		vInventory -5316
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioHeatBulb of InventItem
	(properties
		verb 33
		noun 17
		vInventory -5315
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioChaseLights of InventItem
	(properties
		verb 34
		noun 9
		vInventory -5314
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioRemote of InventItem
	(properties
		verb 35
		noun 38
		vInventory -5313
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioHorseshoe of InventItem
	(properties
		verb 36
		noun 18
		vInventory -5312
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioToiletPaper of InventItem
	(properties
		verb 37
		noun 47
		vInventory -5311
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioSouvenirDice of InventItem
	(properties
		verb 38
		noun 14
		vInventory -5310
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioShavedDice of InventItem
	(properties
		verb 39
		noun 13
		vInventory -5309
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioLegalDice of InventItem
	(properties
		verb 40
		noun 12
		vInventory -5308
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioMoney of InventItem
	(properties
		verb 72
		noun 49
		vInventory -5307
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioMagPage of InventItem
	(properties
		verb 73
		noun 50
		vInventory -5306
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioPot of InventItem
	(properties
		verb 41
		noun 36
		vInventory -5305
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioSalt of InventItem
	(properties
		verb 42
		noun 39
		vInventory -5304
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioBeaverMilk of InventItem
	(properties
		verb 43
		noun 2
		vInventory -5303
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioLimeJuice of InventItem
	(properties
		verb 45
		noun 25
		vInventory -5302
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioMold of InventItem
	(properties
		verb 44
		noun 28
		vInventory -5301
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioBeaverCheese of InventItem
	(properties
		verb 46
		noun 1
		vInventory -5300
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioKumquat of InventItem
	(properties
		verb 47
		noun 22
		vInventory -5299
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioQuiche of InventItem
	(properties
		verb 48
		noun 37
		vInventory -5298
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioOrgasmPowder of InventItem
	(properties
		verb 49
		noun 32
		vInventory -5297
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioQuichePlus of InventItem
	(properties
		verb 74
		noun 51
		vInventory -5296
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioScrewdriver of InventItem
	(properties
		verb 50
		noun 41
		vInventory -5295
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioJumperWire of InventItem
	(properties
		verb 51
		noun 20
		vInventory -5294
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioKnife of InventItem
	(properties
		verb 52
		noun 8
		vInventory -5293
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioPolyester of InventItem
	(properties
		verb 53
		noun 35
		vInventory -5292
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioKZJelly of InventItem
	(properties
		verb 54
		noun 23
		vInventory -5291
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioHanky of InventItem
	(properties
		verb 55
		noun 16
		vInventory -5290
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioHankyLubed of InventItem
	(properties
		verb 56
		noun 26
		vInventory -5289
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioBowlingBall of InventItem
	(properties
		verb 57
		noun 7
		vInventory -5288
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioInsurance of InventItem
	(properties
		verb 58
		noun 19
		vInventory -5287
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioStock of InventItem
	(properties
		verb 59
		noun 44
		vInventory -5286
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
	)
)

(instance ioHairWeaveKit of InventItem
	(properties
		verb 98
		noun 52
		vInventory -5285
	)
	
	(method (init)
		(super init: &rest)
		(proc64038_1 self)
		(self addHotspotVerb: 61)
	)
	
	(method (doVerb theVerb)
		(switch theVerb
			(1
				(super doVerb: theVerb)
				(curRoom notify: 5)
			)
			(61
				(ego put: self)
				(ego get: ioNeedle)
				(curRoom notify: 7)
			)
			(else  (super doVerb: theVerb))
		)
	)
)
