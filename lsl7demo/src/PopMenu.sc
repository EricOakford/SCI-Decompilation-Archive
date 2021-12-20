;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64038)
(include sci.sh)
(use Main)
(use DialogPlane)
(use NewUser)
(use MenuItem)
(use GenDialog)
(use Plane)
(use String)
(use System)

(public
	oPopupMenuHandler 0
	proc64038_1 1
	proc64038_2 2
	proc64038_3 3
)

(local
	local0
	local1
	local2
	local3
	local4 =  1
	local5 =  1
)
(procedure (proc64038_1 param1)
	(if (not param1)
		(MonoOut
			{Adding default verbs to NULL object. DJM popmenu.sc}
		)
		(return)
	)
	(param1 addHotspotVerb: 1 3 12)
)

(procedure (proc64038_2 param1)
	(if (or (< argc 1) param1)
		(= local4 1)
	else
		(= local4 0)
	)
)

(procedure (proc64038_3 param1)
	(if (or (< argc 1) param1)
		(= local5 1)
	else
		(= local5 0)
	)
)

(instance oRightClickHandler of EventCode
	(properties)
	
	(method (handleEvent event &tmp eventX eventY newPlaneGetMainCast newPlane newMenuHandler newMenuHandlerShowPopup temp6)
		(if
			(and
				(user canControl:)
				(== (event type?) evMOUSEBUTTON)
				(& (event modifiers?) emSHIFT)
			)
			(if (not local4) (event claimed?) (return))
			(event claimed: 1)
			(= eventX (event x?))
			(= eventY (event y?))
			((= newPlane (Plane new:)) init: 0 0 639 479)
			(newPlane setPri: 5000)
			(= newPlaneGetMainCast (newPlane getMainCast:))
			((= newMenuHandler (MenuHandler new:))
				bPopup: 1
				nStyle: 1
				nHilitedBack: 238
				nUnhilitedBack: 237
				nHilitedFore: 79
				nUnhilitedFore: 87
				nHeaderBack: 82
				nHeaderFore: 0
				init: newPlaneGetMainCast
				hide:
			)
			(if (not (= temp6 (MakeMessageText 0 0 50 6 14)))
				(= temp6 (Str with: {(Shortcuts)}))
			)
			(newMenuHandler
				addChild: ((MenuHeader new:) oText: temp6 yourself:)
			)
			(if local5
				(newMenuHandler
					addChild:
						((MenuItem new:)
							oText: (MakeMessageText 0 0 50 2 14)
							userData: 2
							yourself:
						)
				)
			)
			(newMenuHandler
				addChild:
					((MenuItem new:)
						oText: (MakeMessageText 0 0 50 1 14)
						userData: 1
						yourself:
					)
			)
			(newMenuHandler posnAt: 0)
			(= newMenuHandlerShowPopup (newMenuHandler showPopup:))
			(if (== curRoomNum 110) (curRoom notify: 9))
			(switch newMenuHandlerShowPopup
				(1
					(if (== ((ScriptID 64037 2) priority?) 450)
						((ScriptID 64037 2) setPri: -1)
					else
						((ScriptID 64037 2) setPri: 450)
					)
					(UpdatePlane (ScriptID 64037 2))
				)
				(2
					(if ((ScriptID 64017 0) test: 0)
						((ScriptID 90 0) dispose:)
					else
						((ScriptID 90 0) init:)
					)
				)
			)
		)
		(event claimed?)
	)
)

(class L7PopupMenuHandler of Obj
	(properties
		scratch 0
	)
	
	(method (init param1 &tmp temp0 temp1 temp2)
		(asm
			_line_   178
			_file_   {filename}
			_line_   179
			pushi    #registerGlobalHandler
			pushi    1
			lofsa    oRightClickHandler
			push    
			lag      gOEventHandler
			send     6
			_line_   182
			pushi    #new
			pushi    0
			class    List
			send     4
			sal      local0
			_line_   183
			pushi    #new
			pushi    0
			class    List
			send     4
			sal      local1
			_line_   184
			ldi      0
			sal      local2
			_line_   185
			ldi      0
			sat      temp0
code_0434:
			lst      temp0
			ldi      250
			lt?     
			bnt      code_049b
			_line_   186
			ldi      1
			sat      temp1
			_line_   187
code_0446:
			ldi      1
			bnt      code_0497
			_line_   188
			pushi    6
			pushi    0
			lst      temp0
			pushi    0
			lst      temp1
			pushi    13
			pushi    1
			calle    MakeMessageText,  12
			sat      temp2
			_line_   189
			bnt      code_048d
			_line_   190
			pushi    #addToEnd
			pushi    1
			push    
			lal      local0
			send     6
			_line_   191
			pushi    #addToEnd
			pushi    1
			lst      temp0
			lal      local1
			send     6
			_line_   192
			+at      temp1
			_line_   193
			+al      local2
			jmp      code_0446
code_048d:
			_line_   194
			_line_   195
			jmp      code_0497
			jmp      code_0446
code_0497:
			+at      temp0
			jmp      code_0434
code_049b:
			_line_   200
			pushi    #new
			pushi    0
			class    Set
			send     4
			sal      local3
			_line_   202
			pushi    #init
			pushi    0
			&rest    param1
			super    Obj,  4
			_line_   203
			ret     
		)
	)
	
	(method (dispose)
		(gOEventHandler
			unregisterGlobalHandler: oRightClickHandler
		)
		(if local0 (local0 dispose:) (= local0 0))
		(if local1 (local1 release: dispose:) (= local1 0))
		(if local3 (local3 release: dispose:) (= local3 0))
		(super dispose: &rest)
	)
	
	(method (handleFeature param1 &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8 temp9 temp10 temp11 temp12 temp13 temp14 temp15 temp16 temp17 temp18 temp19 temp20 temp21 temp22 temp23 temp24 temp25)
		(asm
			_line_   220
			_file_   {filename}
			_line_   222
			_line_   223
			lag      mouseX
			sat      temp0
			_line_   224
			lag      mouseY
			sat      temp1
			_line_   226
			lap      param1
			not     
			bnt      code_0576
			_line_   227
			pushi    1
			lofsa    {Attempt to handle NULL feature. DJM POPMENU.SC}
			push    
			callk    MonoOut,  2
			_line_   228
			ldi      65535
			ret     
code_0576:
			_line_   231
			pushi    #respondsTo
			pushi    1
			pushi    879
			lap      param1
			send     6
			bnt      code_05a5
			_line_   232
			ldi      11
			sat      temp16
			_line_   233
			pushi    #verb
			pushi    0
			pushi    #ioMine
			pushi    0
			lap      param1
			send     4
			send     4
			sat      temp23
			jmp      code_05b6
code_05a5:
			_line_   234
			_line_   235
			lag      curRoomNum
			sat      temp16
			_line_   236
			ldi      65535
			sat      temp23
code_05b6:
			_line_   240
			pushi    #new
			pushi    0
			class    Plane
			send     4
			sat      temp2
			_line_   241
			pushi    #init
			pushi    4
			pushi    0
			pushi    0
			pushi    639
			pushi    479
			send     12
			_line_   242
			_line_   243
			pushi    #setPri
			pushi    1
			pushi    5000
			lat      temp2
			send     6
			_line_   245
			pushi    #getMainCast
			pushi    0
			lat      temp2
			send     4
			sat      temp3
			_line_   246
			pushi    #new
			pushi    0
			class    MenuHandler
			send     4
			sat      temp4
			_line_   247
			_line_   248
			pushi    #bPopup
			pushi    1
			pushi    1
			_line_   249
			pushi    986
			pushi    1
			pushi    1
			_line_   250
			pushi    990
			pushi    1
			pushi    238
			_line_   251
			pushi    989
			pushi    1
			pushi    237
			_line_   252
			pushi    995
			pushi    1
			pushi    79
			_line_   253
			pushi    993
			pushi    1
			pushi    87
			_line_   254
			pushi    1000
			pushi    1
			pushi    82
			_line_   255
			pushi    1001
			pushi    1
			pushi    0
			_line_   256
			pushi    142
			pushi    1
			lst      temp3
			_line_   257
			pushi    105
			pushi    0
			send     58
			_line_   263
			pushi    #getHotspotVerbList
			pushi    0
			lap      param1
			send     4
			sat      temp6
			_line_   264
			not     
			bnt      code_0689
			_line_   265
			pushi    2
			lofsa    {No verb list for feature %d}
			push    
			lsp      param1
			callk    MonoOut,  4
			_line_   266
			ldi      65535
			ret     
code_0689:
			_line_   270
			pushi    #getName
			pushi    0
			lap      param1
			send     4
			sat      temp17
			_line_   271
			not     
			bnt      code_06af
			_line_   272
			pushi    #with
			pushi    1
			lofsa    {An Unnamed Object}
			push    
			class    Str
			send     6
			sat      temp17
code_06af:
			_line_   275
			pushi    #addChild
			pushi    1
			_line_   276
			_line_   277
			pushi    #oText
			pushi    1
			lst      temp17
			_line_   278
			pushi    148
			pushi    0
			pushi    #new
			pushi    0
			class    MenuHeader
			send     4
			sat      temp5
			send     10
			push    
			lat      temp4
			send     6
			_line_   283
			ldi      0
			sat      temp10
			_line_   284
			ldi      0
			sat      temp11
			_line_   285
			pushi    #first
			pushi    0
			lat      temp6
			send     4
			sat      temp7
			_line_   286
code_06fc:
			lat      temp7
			bnt      code_07e1
			_line_   287
			pushi    2
			pushi    8
			push    
			callk    KList,  4
			sat      temp9
			_line_   289
			pushi    5
			pushi    0
			push    
			pushi    0
			pushi    1
			pushi    13
			calle    MakeMessageText,  10
			sat      temp8
			_line_   290
			not     
			bnt      code_073a
			_line_   291
			pushi    #with
			pushi    1
			lofsa    {Missing Verb}
			push    
			class    Str
			send     6
			sat      temp8
code_073a:
			_line_   294
			lst      temp9
			ldi      3
			eq?     
			bt       code_0757
			lst      temp9
			ldi      12
			eq?     
			bnt      code_0767
			lal      local3
			bnt      code_0767
			pushi    #size
			pushi    0
			send     4
			bnt      code_0767
code_0757:
			_line_   295
			pushi    #cat
			pushi    1
			lofsa    {|\10}
			push    
			lat      temp8
			send     6
code_0767:
			_line_   299
			_line_   300
			_line_   301
			pushi    #oText
			pushi    1
			lst      temp8
			_line_   302
			pushi    954
			pushi    1
			lst      temp9
			_line_   303
			pushi    148
			pushi    0
			pushi    #new
			pushi    0
			class    MenuItem
			send     4
			send     16
			sat      temp5
			_line_   307
			lst      temp9
			dup     
			_line_   308
			ldi      3
			eq?     
			bnt      code_07ab
			_line_   309
			lat      temp5
			sat      temp10
			jmp      code_07ce
code_07ab:
			dup     
			_line_   311
			ldi      12
			eq?     
			bnt      code_07bd
			_line_   312
			lat      temp5
			sat      temp11
			jmp      code_07ce
code_07bd:
			_line_   314
			_line_   315
			pushi    #addChild
			pushi    1
			lst      temp5
			lat      temp4
			send     6
code_07ce:
			toss    
			_line_   319
			pushi    #next
			pushi    1
			lst      temp7
			lat      temp6
			send     6
			sat      temp7
			jmp      code_06fc
code_07e1:
			_line_   324
			lat      temp10
			bnt      code_08c8
			_line_   325
			pushi    #addChild
			pushi    1
			push    
			lat      temp4
			send     6
			_line_   327
			pushi    #oInventItemSlots
			pushi    0
			pushi    2
			pushi    64037
			pushi    0
			callk    ScriptID,  4
			send     4
			sat      temp12
			_line_   328
			pushi    #invSlotsTot
			pushi    0
			pushi    2
			pushi    64037
			pushi    0
			callk    ScriptID,  4
			send     4
			sat      temp13
			_line_   329
			ldi      0
			sat      temp15
code_0827:
			lst      temp15
			lat      temp13
			lt?     
			bnt      code_08c8
			_line_   330
			pushi    #at
			pushi    1
			lst      temp15
			lat      temp12
			send     6
			sat      temp14
			_line_   331
			bnt      code_08c3
			_line_   332
			pushi    #verb
			pushi    0
			send     4
			sat      temp9
			_line_   335
			push    
			lat      temp23
			eq?     
			bnt      code_085f
			_line_   336
			jmp      code_08c3
code_085f:
			_line_   339
			pushi    5
			pushi    0
			lst      temp9
			pushi    0
			pushi    1
			pushi    11
			calle    MakeMessageText,  10
			sat      temp8
			_line_   340
			not     
			bnt      code_088c
			_line_   341
			pushi    #with
			pushi    1
			lofsa    {Missing Inventory Name}
			push    
			class    Str
			send     6
			sat      temp8
code_088c:
			_line_   344
			pushi    #addChild
			pushi    1
			_line_   345
			_line_   346
			pushi    #oText
			pushi    1
			lst      temp8
			_line_   347
			pushi    954
			pushi    1
			lst      temp9
			_line_   348
			pushi    148
			pushi    0
			pushi    #new
			pushi    0
			class    MenuItem
			send     4
			sat      temp5
			send     16
			push    
			lat      temp10
			send     6
code_08c3:
			+at      temp15
			jmp      code_0827
code_08c8:
			_line_   356
			lat      temp11
			bnt      code_09de
			_line_   357
			pushi    #addChild
			pushi    1
			push    
			lat      temp4
			send     6
			_line_   359
			lal      local3
			bnt      code_09de
			pushi    #size
			pushi    0
			send     4
			bnt      code_09de
			_line_   360
			pushi    5
			pushi    0
			pushi    12
			pushi    0
			pushi    1
			pushi    13
			calle    MakeMessageText,  10
			sat      temp8
			_line_   361
			not     
			bnt      code_091b
			_line_   362
			pushi    #with
			pushi    1
			lofsa    {Missing MSG}
			push    
			class    Str
			send     6
			sat      temp8
code_091b:
			_line_   365
			pushi    #addChild
			pushi    1
			_line_   366
			_line_   367
			pushi    #oText
			pushi    1
			lst      temp8
			_line_   368
			pushi    954
			pushi    1
			pushi    12
			_line_   369
			pushi    148
			pushi    0
			pushi    #new
			pushi    0
			class    MenuItem
			send     4
			sat      temp5
			send     16
			push    
			lat      temp11
			send     6
			_line_   373
			pushi    #size
			pushi    0
			lal      local3
			send     4
			sat      temp24
			_line_   374
			ldi      0
			sat      temp15
code_0966:
			lst      temp15
			lat      temp24
			lt?     
			bnt      code_09de
			_line_   375
			pushi    #at
			pushi    1
			lst      temp15
			lal      local3
			send     6
			sat      temp25
			_line_   376
			pushi    #at
			pushi    1
			push    
			lal      local1
			send     6
			sat      temp9
			_line_   378
			pushi    #with
			pushi    1
			pushi    #at
			pushi    1
			lst      temp25
			lal      local0
			send     6
			push    
			class    Str
			send     6
			sat      temp8
			_line_   380
			pushi    #addChild
			pushi    1
			_line_   381
			_line_   382
			pushi    #oText
			pushi    1
			push    
			_line_   383
			pushi    954
			pushi    1
			lst      temp9
			_line_   384
			pushi    148
			pushi    0
			pushi    #new
			pushi    0
			class    MenuItem
			send     4
			sat      temp5
			send     16
			push    
			lat      temp11
			send     6
			+at      temp15
			jmp      code_0966
code_09de:
			_line_   392
			pushi    #posnAt
			pushi    1
			pushi    0
			lat      temp4
			send     6
			_line_   395
			pushi    #showPopup
			pushi    0
			lat      temp4
			send     4
			sat      temp9
			_line_   398
			lsg      curRoomNum
			ldi      110
			eq?     
			bnt      code_0a10
			_line_   399
			pushi    #notify
			pushi    1
			pushi    0
			lag      curRoom
			send     6
code_0a10:
			_line_   402
code_0a13:
			lst      temp9
			ldi      12
			eq?     
			bnt      code_0c2a
			_line_   403
			pushi    5
			pushi    0
			pushi    0
			pushi    52
			pushi    1
			pushi    14
			calle    MakeMessageText,  10
			sat      temp20
			_line_   404
			pushi    #getName
			pushi    0
			lap      param1
			send     4
			sat      temp17
			_line_   405
			pushi    #format
			pushi    2
			lst      temp20
			push    
			class    Str
			send     8
			sat      temp21
			_line_   406
			pushi    1
			lst      temp20
			calle    proc64896_7,  2
			_line_   407
			pushi    1
			lst      temp17
			calle    proc64896_7,  2
			_line_   409
			_line_   410
			pushi    4
			_line_   411
			lst      temp21
			_line_   412
			pushi    5
			pushi    0
			pushi    0
			pushi    17
			pushi    1
			pushi    14
			calle    MakeMessageText,  10
			push    
			_line_   413
			pushi    5
			pushi    0
			pushi    0
			pushi    18
			pushi    1
			pushi    14
			calle    MakeMessageText,  10
			push    
			_line_   414
			pushi    20
			calle    proc64033_6,  8
			sat      temp19
			_line_   417
			not     
			bnt      code_0ab8
			_line_   418
			ldi      65535
			ret     
code_0ab8:
			_line_   420
			pushi    #lower
			pushi    0
			lat      temp19
			send     4
			_line_   422
			ldi      0
			sat      temp15
code_0acb:
			lst      temp15
			lal      local2
			lt?     
			bnt      code_0bb7
			_line_   423
			pushi    #with
			pushi    1
			pushi    #at
			pushi    1
			lst      temp15
			lal      local0
			send     6
			push    
			class    Str
			send     6
			sat      temp8
			_line_   424
			pushi    #lower
			pushi    0
			send     4
			_line_   425
			pushi    #size
			pushi    0
			lat      temp8
			send     4
			sat      temp22
			_line_   426
			pushi    #left
			pushi    1
			push    
			lat      temp19
			send     6
			sat      temp18
			_line_   427
			pushi    #weigh
			pushi    1
			push    
			lat      temp8
			send     6
			push    
			ldi      0
			eq?     
			bnt      code_0b98
			_line_   428
			pushi    2
			lofsa    {Hit on '%s'}
			push    
			lst      temp8
			callk    MonoOut,  4
			_line_   429
			pushi    1
			lst      temp18
			calle    proc64896_7,  2
			_line_   430
			pushi    1
			lst      temp8
			calle    proc64896_7,  2
			_line_   431
			pushi    #at
			pushi    1
			lst      temp15
			lal      local1
			send     6
			sat      temp9
			_line_   432
			pushi    #addToEnd
			pushi    1
			lst      temp15
			lal      local3
			send     6
			_line_   433
code_0b6e:
			pushi    #size
			pushi    0
			lal      local3
			send     4
			push    
			ldi      20
			gt?     
			bnt      code_0b93
			_line_   434
			pushi    #delete
			pushi    1
			pushi    #at
			pushi    1
			pushi    0
			lal      local3
			send     6
			push    
			lal      local3
			send     6
			jmp      code_0b6e
code_0b93:
			_line_   436
			jmp      code_0bb7
code_0b98:
			_line_   438
			pushi    1
			lst      temp18
			calle    proc64896_7,  2
			_line_   439
			pushi    1
			lst      temp8
			calle    proc64896_7,  2
			+at      temp15
			jmp      code_0acb
code_0bb7:
			_line_   442
			pushi    1
			lst      temp19
			calle    proc64896_7,  2
			_line_   444
			lst      temp9
			ldi      12
			eq?     
			bnt      code_0a13
			_line_   445
			_line_   446
			pushi    3
			_line_   447
			pushi    5
			pushi    0
			pushi    0
			pushi    54
			pushi    1
			pushi    14
			calle    MakeMessageText,  10
			push    
			_line_   448
			pushi    5
			pushi    0
			pushi    0
			pushi    17
			pushi    1
			pushi    14
			calle    MakeMessageText,  10
			push    
			_line_   449
			pushi    5
			pushi    0
			pushi    0
			pushi    18
			pushi    1
			pushi    14
			calle    MakeMessageText,  10
			push    
			calle    proc64033_5,  6
			not     
			bnt      code_0a13
			_line_   451
			_line_   452
			ldi      65535
			ret     
			jmp      code_0a13
code_0c2a:
			_line_   459
			lat      temp9
			ret     
		)
	)
)

(instance oPopupMenuHandler of L7PopupMenuHandler
	(properties)
)
