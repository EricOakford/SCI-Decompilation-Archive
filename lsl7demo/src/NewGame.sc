;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64888)
(include sci.sh)
(use Main)
(use oMenuPopupPlane)
(use DialogPlane)
(use n64866)
(use NewUser)
(use GenDialog)
(use String)
(use Sound)
(use System)

(public
	DisabledPlanes 0
	proc64888_1 1
	proc64888_2 2
	proc64888_3 3
	proc64888_4 4
	proc64888_5 5
	proc64888_6 6
	AddPlane 7
	RestorePlane 8
	oPlaneStack 9
)

(procedure (proc64888_1 &tmp temp0 gDisabledPlanesSize)
	(= gDisabledPlanesSize (gDisabledPlanes size:))
	(= temp0 0)
	(while (< temp0 gDisabledPlanesSize)
		((gDisabledPlanes at: 0) enable:)
		(++ temp0)
	)
)

(procedure (proc64888_2 &tmp temp0 planesSize)
	(= planesSize (planes size:))
	(= temp0 0)
	(while (< temp0 planesSize)
		((planes at: 0) disable:)
		(++ temp0)
	)
)

(procedure (proc64888_3 param1 &tmp temp0)
	(= temp0 0)
	(while (< temp0 argc)
		([param1 temp0] enable:)
		(++ temp0)
	)
)

(procedure (proc64888_4 param1 &tmp temp0)
	(= temp0 0)
	(while (< temp0 argc)
		([param1 temp0] disable:)
		(++ temp0)
	)
)

(procedure (proc64888_5)
	(proc64888_2)
	(proc64888_3 &rest)
)

(procedure (proc64888_6)
	(proc64888_1)
	(proc64888_4 &rest)
)

(procedure (AddPlane &tmp temp0)
	(oPlaneStack addToFront: 0)
	(= temp0 0)
	(while (< temp0 (planes size:))
		(oPlaneStack addToFront: (planes at: temp0))
		(++ temp0)
	)
)

(procedure (RestorePlane &tmp temp0)
	(if (oPlaneStack isEmpty:)
		(MonoOut
			{Tried to restore plane when none saved on stack.}
		)
		(return)
	)
	(while (= temp0 (oPlaneStack at: 0))
		(temp0 enable:)
		(oPlaneStack delete: temp0)
	)
	(oPlaneStack delete: 0)
)

(instance DisabledPlanes of Set
	(properties)
	
	(method (eachElementDo param1 &tmp theNextNode temp1 temp2)
		(= theNextNode (KList 3 elements))
		(while theNextNode
			(= nextNode (KList 6 theNextNode))
			(= temp1 ((KList 8 theNextNode) casts?))
			(= theNextNode (KList 3 (temp1 elements?)))
			(while theNextNode
				(temp1 nextNode: (KList 6 theNextNode))
				(= temp2 (KList 8 theNextNode))
				(temp2 eachElementDo: param1 &rest)
				(= theNextNode (temp1 nextNode?))
			)
			(= theNextNode nextNode)
		)
	)
)

(instance oPlaneStack of List
	(properties)
)

(class NewGame of DoitChangedGame
	(properties
		scratch 0
		script 0
		printLang 1
		_detailLevel 3
		panelObj 0
		panelSelector 0
		handsOffCode 0
		handsOnCode 0
		nGameSpeed 7
	)
	
	(method (init)
		((= gDisabledPlanes DisabledPlanes) add:)
		((= gOPlaneStack oPlaneStack) add:)
		(super init: &rest)
	)
	
	(method (replay &tmp temp0 temp1 temp2 temp3 planesNextNode temp5 temp6)
		(= global113 -1)
		(Palette 1 999)
		(= planesNextNode (KList 3 (planes elements?)))
		(while planesNextNode
			(planes nextNode: (KList 6 planesNextNode))
			(= temp1 (KList 8 planesNextNode))
			(AddPlane temp1)
			(= temp5 (KList 3 ((temp1 casts?) elements?)))
			(while temp5
				((temp1 casts?) nextNode: (KList 6 temp5))
				(= temp2 (KList 8 temp5))
				(= temp6 (KList 3 (temp2 elements?)))
				(while temp6
					(temp2 nextNode: (KList 6 temp6))
					(if (& ((= temp3 (KList 8 temp6)) -info-?) $0010)
						(AddScreenItem temp3)
					)
					(= temp6 (temp2 nextNode?))
				)
				(= temp5 ((temp1 casts?) nextNode?))
			)
			(temp1 replay:)
			(= planesNextNode (planes nextNode?))
		)
		(= planesNextNode (KList 3 (gDisabledPlanes elements?)))
		(while planesNextNode
			(gDisabledPlanes nextNode: (KList 6 planesNextNode))
			(= temp1 (KList 8 planesNextNode))
			(AddPlane temp1)
			(= temp5 (KList 3 ((temp1 casts?) elements?)))
			(while temp5
				((temp1 casts?) nextNode: (KList 6 temp5))
				(= temp2 (KList 8 temp5))
				(= temp6 (KList 3 (temp2 elements?)))
				(while temp6
					(temp2 nextNode: (KList 6 temp6))
					(if (& ((= temp3 (KList 8 temp6)) -info-?) $0010)
						(AddScreenItem temp3)
					)
					(= temp6 (temp2 nextNode?))
				)
				(= temp5 ((temp1 casts?) nextNode?))
			)
			(temp1 replay:)
			(= planesNextNode (gDisabledPlanes nextNode?))
		)
		(if lastEvent (lastEvent dispose:))
		(theGame setCursor: waitCursor 1)
		(= temp0
			(if (not (OneOf (curRoom style?) -1 16 17 18 19))
				(curRoom style?)
			else
				0
			)
		)
		(cond 
			(
			(and (not (user canControl:)) (not (user canInput:))) (theGame setCursor: waitCursor))
			((and theIconBar (theIconBar curIcon?)) (theGame setCursor: (theIconBar getCursor:)))
			(else (theGame setCursor: normalCursor))
		)
		(if (IsHiRes) (Font 1 640 480))
		(DoSound sndNOP)
		(Sound pause: 0)
		(= tickOffset (- gameTime (GetTime)))
		(ReadPrefs)
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (newRoom)
		(self handsOff:)
		(super newRoom: &rest)
	)
	
	(method (restore &tmp [temp0 12])
		(proc64866_1)
	)
	
	(method (save &tmp [temp0 6])
		(proc64866_0)
	)
	
	(method (masterVolume param1)
		(if argc
			(DoSound sndINIT param1)
		else
			(DoSound sndINIT)
		)
	)
	
	(method (autosave param1 param2 &tmp temp0 [temp1 2] temp3 temp4 newStr)
		(if (> argc 1) (= temp0 param2) else (= temp0 0))
		((= newStr (Str new:)) copy: curSaveDir)
		(curSaveDir copy: gNewStr_2)
		(if (FileIO 19 (KArray 9 curSaveDir))
			(= temp3 (self setCursor: waitCursor 1))
			(if argc
				(if param1
					(SaveGame
						0
						{Autosv}
						temp0
						(param1 data?)
						(KArray 9 version)
					)
				else
					(SaveGame
						0
						{Autosv}
						temp0
						{A Fresh Game}
						(KArray 9 version)
					)
				)
			else
				(SaveGame
					0
					{Autosv}
					temp0
					{Where I last left off...}
					(KArray 9 version)
				)
			)
			(self setCursor: temp3 (HaveMouse))
		)
		(curSaveDir copy: newStr)
		(newStr dispose:)
	)
	
	(method (autorestore param1 &tmp temp0 newStr_4 newStr_2 newStr_3 planesNextNode temp5 temp6 temp7 temp8 temp9 temp10 temp11 newStr)
		(if argc (= temp0 param1) else (= temp0 0))
		((= newStr (Str new:)) copy: curSaveDir)
		(curSaveDir copy: gNewStr_2)
		(if
		(not (SaveGame 3 {Autosv} temp0 (KArray 9 version)))
			(if temp0
				(proc64033_0
					(MakeMessageText 0 0 2 1 14)
					(Str with: global288)
				)
				(= quit 1)
			)
			(curSaveDir copy: newStr)
			(newStr dispose:)
			(return)
		)
		(= newStr_2 (Str new:))
		(= newStr_3 (Str new:))
		(= newStr_4 (Str new:))
		(Sound pause: 1)
		(= planesNextNode (KList 3 (planes elements?)))
		(while planesNextNode
			(planes nextNode: (KList 6 planesNextNode))
			(= temp7 (KList 8 planesNextNode))
			(= temp5 (KList 3 ((temp7 casts?) elements?)))
			(while temp5
				((temp7 casts?) nextNode: (KList 6 temp5))
				(= temp8 (KList 8 temp5))
				(= temp6 (KList 3 (temp8 elements?)))
				(while temp6
					(temp8 nextNode: (KList 6 temp6))
					(if
						(= temp10
							(& ((= temp9 (KList 8 temp6)) -info-?) $0010)
						)
						(DeleteScreenItem temp9)
						(temp9 -info-: (| (temp9 -info-?) temp10))
					)
					(= temp6 (temp8 nextNode?))
				)
				(= temp5 ((temp7 casts?) nextNode?))
			)
			(DeletePlane temp7)
			(= planesNextNode (planes nextNode?))
		)
		(= planesNextNode (KList 3 (gDisabledPlanes elements?)))
		(while planesNextNode
			(gDisabledPlanes nextNode: (KList 6 planesNextNode))
			(= temp7 (KList 8 planesNextNode))
			(= temp5 (KList 3 ((temp7 casts?) elements?)))
			(while temp5
				((temp7 casts?) nextNode: (KList 6 temp5))
				(= temp8 (KList 8 temp5))
				(= temp6 (KList 3 (temp8 elements?)))
				(while temp6
					(temp8 nextNode: (KList 6 temp6))
					(if
						(= temp10
							(& ((= temp9 (KList 8 temp6)) -info-?) $0010)
						)
						(DeleteScreenItem temp9)
						(temp9 -info-: (| (temp9 -info-?) temp10))
					)
					(= temp6 (temp8 nextNode?))
				)
				(= temp5 ((temp7 casts?) nextNode?))
			)
			(DeletePlane temp7)
			(= planesNextNode (gDisabledPlanes nextNode?))
		)
		(self setCursor: waitCursor 1)
		(SaveGame 1 {Autosv} temp0 (KArray 9 version))
		(proc64033_0
			(MakeMessageText 0 0 2 1 14)
			(Str with: global288)
		)
		(= quit 1)
		(self setCursor: temp11 (HaveMouse))
		(Sound pause: 0)
		(newStr_2 dispose:)
		(newStr_3 dispose:)
		(newStr_4 dispose:)
		(curSaveDir copy: newStr)
		(newStr dispose:)
	)
	
	(method (saveThis param1 param2 &tmp temp0 [temp1 3])
		(if numCD (self getDisc: numCD))
		(= temp0 (self setCursor: waitCursor 1))
		(gNewStr copy: param2)
		(if
			(not
				(SaveGame
					0
					name
					param1
					(param2 data?)
					(KArray 9 version)
				)
			)
			(proc64033_0
				(MakeMessageText 10 0 0 1 -542)
				(Str with: global288)
			)
		else
			(= global113 param1)
			(self autosave: param2)
			(gNewStr copy: param2)
		)
		(self setCursor: temp0 (HaveMouse))
	)
	
	(method (restoreThis param1 &tmp [temp0 4] temp4 temp5 temp6 temp7 planesNextNode temp9 temp10)
		(if
		(not (SaveGame 3 name param1 (KArray 9 version)))
			(proc64033_0
				(MakeMessageText 3 0 0 1 -542)
				(Str with: global288)
			)
			(return)
		)
		(= planesNextNode (KList 3 (planes elements?)))
		(while planesNextNode
			(planes nextNode: (KList 6 planesNextNode))
			(= temp4 (KList 8 planesNextNode))
			(= temp9 (KList 3 ((temp4 casts?) elements?)))
			(while temp9
				((temp4 casts?) nextNode: (KList 6 temp9))
				(= temp5 (KList 8 temp9))
				(= temp10 (KList 3 (temp5 elements?)))
				(while temp10
					(temp5 nextNode: (KList 6 temp10))
					(if
						(= temp7
							(& ((= temp6 (KList 8 temp10)) -info-?) $0010)
						)
						(DeleteScreenItem temp6)
						(temp6 -info-: (| (temp6 -info-?) temp7))
					)
					(= temp10 (temp5 nextNode?))
				)
				(= temp9 ((temp4 casts?) nextNode?))
			)
			(DeletePlane temp4)
			(= planesNextNode (planes nextNode?))
		)
		(= planesNextNode (KList 3 (gDisabledPlanes elements?)))
		(while planesNextNode
			(gDisabledPlanes nextNode: (KList 6 planesNextNode))
			(= temp4 (KList 8 planesNextNode))
			(= temp9 (KList 3 ((temp4 casts?) elements?)))
			(while temp9
				((temp4 casts?) nextNode: (KList 6 temp9))
				(= temp5 (KList 8 temp9))
				(= temp10 (KList 3 (temp5 elements?)))
				(while temp10
					(temp5 nextNode: (KList 6 temp10))
					(if
						(= temp7
							(& ((= temp6 (KList 8 temp10)) -info-?) $0010)
						)
						(DeleteScreenItem temp6)
						(temp6 -info-: (| (temp6 -info-?) temp7))
					)
					(= temp10 (temp5 nextNode?))
				)
				(= temp9 ((temp4 casts?) nextNode?))
			)
			(DeletePlane temp4)
			(= planesNextNode (gDisabledPlanes nextNode?))
		)
		(self setCursor: waitCursor 1)
		(SaveGame 1 name param1 (KArray 9 version))
	)
)
