;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64888)
(include sci.sh)
(use Main)
(use List)
(use Set)
(use Event)
(use Events)
(use String)
(use Print)
(use Sound)
(use Save)
(use System)

(public
	DisabledPlanes 0
	proc64888_1 1
	proc64888_2 2
	proc64888_3 3
	proc64888_4 4
	proc64888_5 5
	proc64888_6 6
	proc64888_7 7
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

(procedure (proc64888_7 &tmp temp0)
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
		oCantBeHereHandler 6
	)
	
	(method (init)
		((= gDisabledPlanes DisabledPlanes) add:)
		((= gOPlaneStack oPlaneStack) add:)
		(super init: &rest)
	)
	
	(method (replay &tmp temp0 temp1 temp2 temp3 planesNextNode temp5 temp6)
		(= global113 -1)
		(Palette 1 999)
		(switch global202
			(1 (Palette 1 10000))
			(2 (Palette 1 20000))
			(3 (Palette 1 30000))
			(4 (Palette 1 -25536))
			(5 (Palette 1 -15536))
		)
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
		(while (not quit)
			(self doit:)
		)
	)
	
	(method (newRoom)
		(self handsOff:)
		(super newRoom: &rest)
	)
	
	(method (restore &tmp temp0 temp1 newStr newStr_2 newStr_3 temp5 temp6 temp7 temp8 planesNextNode temp10 temp11)
		(= newStr (Str new:))
		(= newStr_2 (Str new:))
		(= newStr_3 (Str new:))
		(Palette 1 999)
		(if (not (FileIO 19 (KString 9 curSaveDir)))
			(Message 0 -542 9 0 0 1 (newStr data?))
			(newStr_3 format: newStr curSaveDir)
			(Print
				font: 999
				fore: 0
				back: (Palette 3 127 127 127)
				addText: newStr_3
				addButtonBM: -1 0 0 1 {OK} 0 30
				init:
			)
			(GetDirectory curSaveDir)
		)
		(Load rsFONT smallFont)
		(ScriptID 64990)
		(Sound pause: 1)
		(if (!= (= temp0 (Restore doit: &rest)) -1)
			(= planesNextNode (KList 3 (planes elements?)))
			(while planesNextNode
				(planes nextNode: (KList 6 planesNextNode))
				(= temp5 (KList 8 planesNextNode))
				(= temp10 (KList 3 ((temp5 casts?) elements?)))
				(while temp10
					((temp5 casts?) nextNode: (KList 6 temp10))
					(= temp6 (KList 8 temp10))
					(= temp11 (KList 3 (temp6 elements?)))
					(while temp11
						(temp6 nextNode: (KList 6 temp11))
						(if
							(= temp8
								(& ((= temp7 (KList 8 temp11)) -info-?) $0010)
							)
							(DeleteScreenItem temp7)
							(temp7 -info-: (| (temp7 -info-?) temp8))
						)
						(= temp11 (temp6 nextNode?))
					)
					(= temp10 ((temp5 casts?) nextNode?))
				)
				(DeletePlane temp5)
				(= planesNextNode (planes nextNode?))
			)
			(= planesNextNode (KList 3 (gDisabledPlanes elements?)))
			(while planesNextNode
				(gDisabledPlanes nextNode: (KList 6 planesNextNode))
				(= temp5 (KList 8 planesNextNode))
				(= temp10 (KList 3 ((temp5 casts?) elements?)))
				(while temp10
					((temp5 casts?) nextNode: (KList 6 temp10))
					(= temp6 (KList 8 temp10))
					(= temp11 (KList 3 (temp6 elements?)))
					(while temp11
						(temp6 nextNode: (KList 6 temp11))
						(if
							(= temp8
								(& ((= temp7 (KList 8 temp11)) -info-?) $0010)
							)
							(DeleteScreenItem temp7)
							(temp7 -info-: (| (temp7 -info-?) temp8))
						)
						(= temp11 (temp6 nextNode?))
					)
					(= temp10 ((temp5 casts?) nextNode?))
				)
				(DeletePlane temp5)
				(= planesNextNode (gDisabledPlanes nextNode?))
			)
			(self setCursor: waitCursor 1)
			(if (SaveGame 3 name temp0 (KString 9 version))
				(self getDisc: (CD 1))
				(SaveGame 1 name temp0 version)
			else
				(= planesNextNode (KList 3 (planes elements?)))
				(while planesNextNode
					(planes nextNode: (KList 6 planesNextNode))
					(= temp5 (KList 8 planesNextNode))
					(AddPlane temp5)
					(= temp10 (KList 3 ((temp5 casts?) elements?)))
					(while temp10
						((temp5 casts?) nextNode: (KList 6 temp10))
						(= temp6 (KList 8 temp10))
						(= temp11 (KList 3 (temp6 elements?)))
						(while temp11
							(temp6 nextNode: (KList 6 temp11))
							(if
							(& ((= temp7 (KList 8 temp11)) -info-?) $0010)
								(AddScreenItem temp7)
							)
							(= temp11 (temp6 nextNode?))
						)
						(= temp10 ((temp5 casts?) nextNode?))
					)
					(= planesNextNode (planes nextNode?))
				)
				(= planesNextNode (KList 3 (gDisabledPlanes elements?)))
				(while planesNextNode
					(gDisabledPlanes nextNode: (KList 6 planesNextNode))
					(= temp5 (KList 8 planesNextNode))
					(AddPlane temp5)
					(= temp10 (KList 3 ((temp5 casts?) elements?)))
					(while temp10
						((temp5 casts?) nextNode: (KList 6 temp10))
						(= temp6 (KList 8 temp10))
						(= temp11 (KList 3 (temp6 elements?)))
						(while temp11
							(temp6 nextNode: (KList 6 temp11))
							(if
							(& ((= temp7 (KList 8 temp11)) -info-?) $0010)
								(AddScreenItem temp7)
							)
							(= temp11 (temp6 nextNode?))
						)
						(= temp10 ((temp5 casts?) nextNode?))
					)
					(= planesNextNode (gDisabledPlanes nextNode?))
				)
				(Message 0 -542 3 0 0 1 (newStr data?))
				(Message 0 -542 2 0 0 1 (newStr_2 data?))
				(Print
					font: 999
					fore: 0
					back: (Palette 3 127 127 127)
					addText: (newStr data?)
					addButtonBM: -1 0 0 1 (newStr_2 data?) 0 40
					init:
				)
				(self setCursor: temp1 (HaveMouse))
			)
		)
		(Sound pause: 0)
		(newStr dispose:)
		(newStr_2 dispose:)
		(newStr_3 dispose:)
	)
	
	(method (save &tmp newStr temp1 temp2 newStr_3 newStr_4 newStr_2)
		(= newStr (Str new:))
		(if (not (FileIO 19 (KString 9 curSaveDir)))
			(= newStr_2 (Str new:))
			(= newStr_3 (Str new:))
			(Message 0 -542 9 0 0 1 (newStr_3 data?))
			(newStr_2 format: newStr_3 curSaveDir)
			(Print
				font: 999
				fore: 0
				back: (Palette 3 127 127 127)
				addText: newStr_2
				addButtonBM: -1 0 0 1 {OK} 0 30
				init:
			)
			(GetDirectory curSaveDir)
			(newStr_2 dispose:)
			(newStr_3 dispose:)
		)
		(Load rsFONT smallFont)
		(ScriptID 64990)
		(Sound pause: 1)
		(switch (= temp1 (Save doit: newStr))
			(-1)
			(-2
				(= newStr_3 (Str new:))
				(= newStr_4 (Str new:))
				(Message 0 -542 11 0 0 1 (newStr_3 data?))
				(Message 0 -542 2 0 0 1 (newStr_4 data?))
				(if
					(Print
						font: 999
						fore: 0
						back: (Palette 3 127 127 127)
						addText: newStr_3
						addButtonBM: -546 0 0 0 newStr_4 0 40
						addButtonBM: -546 2 0 1 {Change Dir} 55 40
						init:
					)
					(GetDirectory curSaveDir)
				)
				(newStr_3 dispose:)
				(newStr_4 dispose:)
			)
			(else 
				(if numCD (self getDisc: numCD))
				(= temp2 (self setCursor: waitCursor 1))
				(if
					(not
						(SaveGame
							0
							name
							temp1
							(newStr data?)
							(KString 9 version)
						)
					)
					(= newStr_3 (Str new:))
					(= newStr_4 (Str new:))
					(Message 0 -542 10 0 0 1 (newStr_3 data?))
					(Message 0 -542 2 0 0 1 (newStr_4 data?))
					(Print
						font: 999
						fore: 0
						back: (Palette 3 127 127 127)
						addText: newStr_3
						addButtonBM: -1 0 0 1 newStr_4 0 40
						init:
					)
					(newStr_3 dispose:)
					(newStr_4 dispose:)
				)
				(= global113 temp1)
				(self addVerbHandler: newStr)
				(self setCursor: temp2 (HaveMouse))
			)
		)
		(Sound pause: 0)
		(gNewStr copy: newStr)
		(newStr dispose:)
	)
	
	(method (masterVolume param1)
		(if argc
			(DoSound sndINIT param1)
		else
			(DoSound sndINIT)
		)
	)
	
	(method (addVerbHandler param1 param2 &tmp temp0 [temp1 2] temp3 temp4 newStr)
		(if (> argc 1) (= temp0 param2) else (= temp0 0))
		((= newStr (Str new:)) copy: curSaveDir)
		(curSaveDir copy: gameDir)
		(if (FileIO 19 (KString 9 curSaveDir))
			(= temp3 (self setCursor: waitCursor 1))
			(if argc
				(if param1
					(SaveGame
						0
						{Autosave}
						temp0
						(param1 data?)
						(KString 9 version)
					)
				else
					(SaveGame
						0
						{Autosave}
						temp0
						{A Fresh Game}
						(KString 9 version)
					)
				)
			else
				(SaveGame
					0
					{Autosave}
					temp0
					{Where I last left off...}
					(KString 9 version)
				)
			)
			(self setCursor: temp3 (HaveMouse))
			(while ((= temp4 (Event new: 3)) type?)
				(temp4 dispose:)
			)
			(temp4 dispose:)
		)
		(curSaveDir copy: newStr)
		(newStr dispose:)
	)
	
	(method (deleteVerbHandler param1 &tmp temp0 newStr_4 newStr_2 newStr_3 planesNextNode temp5 temp6 temp7 temp8 temp9 temp10 temp11 newStr)
		(if argc (= temp0 param1) else (= temp0 0))
		((= newStr (Str new:)) copy: curSaveDir)
		(curSaveDir copy: gameDir)
		(if
		(not (SaveGame 3 {Autosave} temp0 (KString 9 version)))
			(if temp0
				(Prints
					{Unable to reset game. Please exit and restart application.}
				)
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
		(self getDisc: (CD 1))
		(SaveGame 1 {Autosave} temp0 version)
		(Message 0 -542 3 0 0 1 (newStr_2 data?))
		(Message 0 -542 2 0 0 1 (newStr_3 data?))
		(Print
			font: 999
			fore: 0
			back: (Palette 3 127 127 127)
			addText: (newStr_2 data?)
			addButtonBM: -1 0 0 1 (newStr_3 data?) 0 40
			init:
		)
		(self setCursor: temp11 (HaveMouse))
		(Sound pause: 0)
		(newStr_2 dispose:)
		(newStr_3 dispose:)
		(newStr_4 dispose:)
		(curSaveDir copy: newStr)
		(newStr dispose:)
	)
)
