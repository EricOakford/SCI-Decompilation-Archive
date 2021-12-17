;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64881)
(include sci.sh)
(use Main)
(use Intrface)
(use Event)
(use aeAnimPlayerClass)
(use String)
(use Print)
(use Actor)
(use System)


(instance numNuts of View
	(properties)
)

(class aeActorVisClass of aeCommand
	(properties
		scratch 0
		type $0013
		size 6
		vTiles 0
		whoToCue 0
		realSetButton 0
	)
	
	(method (init theVTiles theRealSetButton)
		(= vTiles theVTiles)
		(= realSetButton theRealSetButton)
	)
	
	(method (save param1)
		(super save: param1)
		(param1 writeWord: realSetButton)
	)
)

(class aeActorPriorityClass of aeCommand
	(properties
		scratch 0
		type $0004
		size 6
		vTiles 0
		whoToCue 0
		priority 0
	)
	
	(method (init theVTiles thePriority)
		(= vTiles theVTiles)
		(= priority thePriority)
	)
	
	(method (save param1)
		(super save: param1)
		(param1 writeWord: priority)
	)
)

(class aeActorCelClass of aeCommand
	(properties
		scratch 0
		type $000f
		size 6
		vTiles 0
		whoToCue 0
		cel 0
	)
	
	(method (init theVTiles theCel)
		(= vTiles theVTiles)
		(= cel theCel)
	)
	
	(method (save param1)
		(super save: param1)
		(param1 writeWord: cel)
	)
)

(class aeActorXYClass of aeCommand
	(properties
		scratch 0
		type $0006
		size 8
		vTiles 0
		whoToCue 0
		x 0
		y 0
	)
	
	(method (init theVTiles theX theY)
		(= vTiles theVTiles)
		(= x theX)
		(= y theY)
	)
	
	(method (save param1)
		(super save: param1)
		(param1 writeWord: x)
		(param1 writeWord: y)
	)
)

(class actor of aeCastMember
	(properties
		scratch 0
		whoToCue 0
		getBitmap 0
		voBG -1
		oSpecialSync -1
		nSpecialSelector 0
		nCutoff 0
		bSpecial 0
		type $0001
		back 234
		view 0
		loop 0
	)
	
	(method (edit &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 printInit)
		(= temp6 0)
		(= temp0 (Str with: {0}))
		(= temp1 (Str with: {0}))
		(= temp3 (+ (= temp2 5) 100))
		(= temp4 5)
		(= temp5 (+ 7 (Font 0 inputFont)))
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {Actor Edit}
			addText: {Name} temp2 temp4
			addEdit: nSpecialSelector 32 temp3 temp4
			first: 3
			addText: {View} temp2 (= temp4 (+ temp4 temp5))
			addEdit: temp0 5 temp3 temp5
			addText: {Loop} temp2 (= temp4 (+ temp4 temp5))
			addEdit: temp1 5 temp3 temp5
			addButton: -1 {CANCEL} temp2 (= temp4 (+ temp4 temp5))
		)
		(if (> (= printInit (Print init:)) 0)
			(= view (temp0 asInteger:))
			(if (ResCheck 128 view)
				(= loop (temp1 asInteger:))
				(numNuts view: view loop: loop)
				(if (>= loop 0)
					(if (and (>= loop 0) (< loop (NumLoops numNuts)))
						(if (> (NumCels numNuts) 0)
							(nCutoff
								format: {%d/%s = %s: %hu/%d} getBitmap name nSpecialSelector view loop
							)
							(self doHeld: aeActorXYClass 0 200 200)
							(self doHeld: aeActorVisClass 0 1)
							(= temp6 1)
						else
							(MonoOut {View %s, loop %s has no cels} temp0 temp1)
						)
					else
						(MonoOut {View %s, loop #%s too large} temp0 temp1)
						(MonoOut {Maximum of %d} (- (NumLoops numNuts) 1))
					)
				else
					(MonoOut {Loop %s must be >= 0} temp1)
				)
			else
				(MonoOut {View %s not found} temp0)
			)
		)
		(temp0 dispose:)
		(temp1 dispose:)
		(return temp6)
	)
	
	(method (doClick &tmp temp0 temp1 temp2 temp3)
		(= temp1 (+ (= temp0 5) 100))
		(= temp2 5)
		(= temp3 (+ 7 (Font 0 inputFont)))
		(Print
			x: 20
			y: 20
			width: 400
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {Actor Help}
			addText: {An actor has a visible representation on the screen} temp0 temp2
			addText:
				{An actor's view and loop number cannot be}
				temp0
				(= temp2 (+ temp2 temp3))
			addText: {changed during an animation.} temp0 (= temp2 (+ temp2 temp3))
			addText: {} temp0 (= temp2 (+ temp2 temp3))
			addText: {It supports these commands:} temp0 (= temp2 (+ temp2 temp3))
			addText:
				{ALT-c____cel_______change the actor's cel number}
				temp0
				(= temp2 (+ temp2 temp3))
			addText: {ALT-p____priority__priority} temp0 (= temp2 (+ temp2 temp3))
			addText:
				{ALT-v____visible___make the actor in/visible}
				temp0
				(= temp2 (+ temp2 temp3))
			addText:
				{ALT-x____xy________change actor's location}
				temp0
				(= temp2 (+ temp2 temp3))
			addText:
				{ALT-y____xy________change actor's location}
				temp0
				(= temp2 (+ temp2 temp3))
			addButton: -1 {OK} temp0 (= temp2 (+ temp2 temp3))
			init:
		)
	)
	
	(method (nClickMethod &tmp bSpecialFirst temp1 temp2)
		(= voBG (= oSpecialSync -1))
		(= bSpecialFirst (bSpecial first:))
		(while bSpecialFirst
			(= temp1 (KList 8 bSpecialFirst))
			(if (temp1 isKindOf: aeActorVisClass)
				(= temp2 (temp1 realSetButton?))
			)
			(if temp2 (self oClickNotify: (temp1 vTiles?)))
			(= bSpecialFirst (bSpecial next: bSpecialFirst))
		)
	)
	
	(method (save param1 &tmp temp0)
		(if
		(& (= temp0 (+ 1 (nSpecialSelector size:))) $0001)
			(nSpecialSelector at: temp0 0)
			(++ temp0)
		)
		(param1 writeWord: type)
		(param1 writeWord: (+ 8 temp0))
		(param1 writeWord: getBitmap)
		(param1 writeWord: view)
		(param1 writeWord: loop)
		(param1 writeWord: temp0)
		(param1 write: (nSpecialSelector data?) temp0)
	)
	
	(method (handleEvent event &tmp temp0)
		(switch (event type?)
			(evKEYBOARD
				(switch (event message?)
					(KEY_h
						(self doClick:)
						(event claimed: 1)
					)
					(KEY_ALT_p
						(self setPri:)
						(event claimed: 1)
					)
					(KEY_ALT_v
						(self doAllButMe:)
						(event claimed: 1)
					)
					(KEY_ALT_x
						(self border:)
						(event claimed: 1)
					)
					(KEY_ALT_y
						(self border:)
						(event claimed: 1)
					)
					(KEY_ALT_e
						(self maxWidth:)
						(event claimed: 1)
					)
				)
			)
			(evMOUSEBUTTON
				(switch (event modifiers?)
					(emALT
						(cond 
							((= temp0 (self vTileOff: event)) (self vTileOn: temp0) (event claimed: 1))
							((self onMe: event) (self bEnabled:) (event claimed: 1))
						)
					)
				)
			)
		)
	)
	
	(method (doAllButMe param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 printInit newStr newStrAsInteger)
		(if (== 2 argc)
			(self doHeld: aeActorVisClass param1 param2)
			(return)
		)
		(= temp2 (+ (= temp1 5) 100))
		(= temp3 5)
		(= temp4 (+ 7 (Font 0 inputFont)))
		(= newStr (Str new:))
		(newStr
			format: {%d} ((whoToCue bTileBorder?) normalize?)
		)
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {Actor Edit}
			addText: {Tick} temp1 temp3
			addEdit: newStr 5 temp2 temp3
			addButton: 1 {SHOW} temp1 (= temp3 (+ temp3 temp4))
			addButton: 0 {HIDE} temp1 (= temp3 (+ temp3 temp4))
			addButton: -1 {CANCEL} temp1 (= temp3 (+ temp3 temp4))
		)
		(if
			(and
				(> (= printInit (Print init:)) 0)
				(>= (= newStrAsInteger (newStr asInteger:)) 0)
			)
			(self doHeld: aeActorVisClass newStrAsInteger printInit)
		)
		(newStr dispose:)
	)
	
	(method (setPri param1 param2 &tmp temp0 temp1 temp2 temp3 temp4 printInit actorNMinWidth newStr newStr_2 newStrAsInteger newStr_2AsInteger)
		(if (== 2 argc)
			(self doHeld: aeActorPriorityClass param1 param2)
			(return)
		)
		(= temp2 (+ (= temp1 5) 100))
		(= temp3 5)
		(= temp4 (+ 7 (Font 0 inputFont)))
		(= newStr (Str new:))
		(newStr
			format: {%d} ((whoToCue bTileBorder?) normalize?)
		)
		(= newStr_2 (Str new:))
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {Actor Edit}
			addText: {Tick} temp1 temp3
			addEdit: newStr 5 temp2 temp3
			addText: {Priority} temp1 (= temp3 (+ temp3 temp4))
			addEdit:
				newStr_2
				5
				temp2
				(if (= actorNMinWidth (self nMinWidth:))
					(newStr_2 format: {%d} (actorNMinWidth priority?))
				else
					(newStr_2 with: {200})
				)
			addButton: -1 {CANCEL} temp1 (= temp3 (+ temp3 temp4))
		)
		(if
			(and
				(> (= printInit (Print init:)) 0)
				(>= (= newStrAsInteger (newStr asInteger:)) 0)
			)
			(= newStr_2AsInteger (newStr_2 asInteger:))
			(self
				doHeld: aeActorPriorityClass newStrAsInteger newStr_2AsInteger
			)
		)
		(newStr dispose:)
		(newStr_2 dispose:)
	)
	
	(method (border param1 param2 param3 &tmp temp0 temp1 temp2 temp3 temp4 printInit actorNMinWidth newStr newStr_2 newStr_3 newStrAsInteger newStr_2AsInteger newStr_3AsInteger)
		(if (== 3 argc)
			(self doHeld: aeActorXYClass param1 param2 param3)
			(return)
		)
		(= temp2 (+ (= temp1 5) 100))
		(= temp3 5)
		(= temp4 (+ 7 (Font 0 inputFont)))
		(= newStr (Str new:))
		(newStr
			format: {%d} ((whoToCue bTileBorder?) normalize?)
		)
		(= newStr_2 (Str new:))
		(= newStr_3 (Str new:))
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {Actor Edit}
			addText: {Tick} temp1 temp3
			addEdit: newStr 5 temp2 temp3
			addText: {X} temp1 (= temp3 (+ temp3 temp4))
			addEdit:
				newStr_2
				5
				temp2
				(if (= actorNMinWidth (self nMinWidth:))
					(newStr_2 format: {%d} (actorNMinWidth x?))
					(newStr_3 format: {%d} (actorNMinWidth y?))
				else
					(newStr_2 with: {200})
					(newStr_3 with: {200})
				)
			addText: {Y} temp1 (= temp3 (+ temp3 temp4))
			addEdit: newStr_3 5 temp2 newStr_3
			addButton: -1 {CANCEL} temp1 (= temp3 (+ temp3 temp4))
		)
		(if
			(and
				(> (= printInit (Print init:)) 0)
				(>= (= newStrAsInteger (newStr asInteger:)) 0)
			)
			(= newStr_2AsInteger (newStr_2 asInteger:))
			(= newStr_3AsInteger (newStr_3 asInteger:))
			(self
				doHeld: aeActorXYClass newStrAsInteger newStr_2AsInteger newStr_3AsInteger
			)
		)
		(newStr dispose:)
		(newStr_2 dispose:)
		(newStr_3 dispose:)
	)
	
	(method (maxWidth &tmp temp0 temp1 temp2 temp3 temp4 temp5 printInit temp7 temp8 newStr newStrAsInteger temp11 temp12)
		(= temp2 (+ (= temp1 5) 100))
		(= temp3 5)
		(= temp4 (+ 7 (Font 0 inputFont)))
		(= newStr (Str new:))
		(newStr
			format: {%d} ((whoToCue bTileBorder?) normalize?)
		)
		(= temp11 (Str newWith: {6}))
		(Print
			x: 20
			y: 20
			width: 200
			fore: 235
			back: 234
			skip: 0
			font: inputFont
			addTitle: {Actor Edit}
			addText: {Tick} temp1 temp3
			addEdit: newStr 5 temp2 temp3
			first: 3
			addText: {Cycle Speed} temp1 (= temp3 (+ temp3 temp4))
			addEdit: temp11 5 temp2 temp11
			addButton: -1 {CANCEL} temp1 (= temp3 (+ temp3 temp4))
		)
		(if
			(and
				(> (= printInit (Print init:)) 0)
				(>= (= newStrAsInteger (newStr asInteger:)) 0)
			)
			(if (> (= temp12 (temp11 asInteger:)) 0)
				(self internalDoSelect: aeActorCelClass)
				(numNuts view: view loop: loop)
				(= temp8 (NumCels numNuts))
				(= temp7 0)
				(while (< temp7 temp8)
					(self
						doHeld: aeActorCelClass (+ newStrAsInteger (* temp7 temp12)) temp7
					)
					(++ temp7)
				)
			else
				(MonoOut {cycle speed must be > 0})
			)
		)
		(newStr dispose:)
		(temp11 dispose:)
	)
	
	(method (nMinWidth)
		(if
			(and
				whoToCue
				(whoToCue bTileBorder?)
				((whoToCue bTileBorder?) isEnabled?)
				(<
					getBitmap
					(((whoToCue bTileBorder?) isEnabled?) size:)
				)
			)
			(return (((whoToCue bTileBorder?) isEnabled?) at: getBitmap))
		)
		(return 0)
	)
	
	(method (vTileOff param1 &tmp actorNMinWidth)
		(return
			(if
				(and
					(= actorNMinWidth (self nMinWidth:))
					(actorNMinWidth onMe: param1)
				)
				(return actorNMinWidth)
			else
				0
			)
		)
	)
	
	(method (vTileOn param1 &tmp temp0 temp1 temp2 temp3)
		(= temp3 (param1 plane?))
		(whoToCue nLeading: self)
		(= temp1 (- (temp0 x?) (param1 x?)))
		(= temp2 (- (temp0 y?) (param1 y?)))
		(repeat
			((= temp0 (Event new: -32768)) localize: temp3)
			(param1
				x: (- (temp0 x?) temp1)
				y: (- (temp0 y?) temp2)
				priority: (- (temp0 y?) temp2)
			)
			(temp0 dispose:)
			(UpdateScreenItem param1)
			(FrameOut)
			(breakif (not (StillDown)))
		)
		(self
			border: ((whoToCue bTileBorder?) normalize?) (param1 x?) (param1 y?)
		)
	)
)

(class aeActorInterceptClass of Obj
	(properties
		scratch 0
		oPrivateText 0
		whoToCue 0
		getBitmap 0
		view 0
		loop 0
	)
	
	(method (init)
		(= oPrivateText (actor new:))
		(oPrivateText
			getBitmap: ((whoToCue bTileBorder?) stopwalk?)
			whoToCue: whoToCue
			view: view
			loop: loop
			init:
		)
		(whoToCue add: oPrivateText)
	)
	
	(method (show)
		(oPrivateText
			doHeld: aeActorVisClass ((whoToCue bTileBorder?) normalize?) 1
		)
	)
	
	(method (hide)
		(oPrivateText
			doHeld: aeActorVisClass ((whoToCue bTileBorder?) normalize?) 0
		)
	)
	
	(method (setPri)
		(oPrivateText
			doHeld: aeActorPriorityClass ((whoToCue bTileBorder?) normalize?) &rest
		)
	)
	
	(method (cel)
		(oPrivateText
			doHeld: aeActorCelClass ((whoToCue bTileBorder?) normalize?) &rest
		)
	)
	
	(method (posn)
		(oPrivateText
			doHeld: aeActorXYClass ((whoToCue bTileBorder?) normalize?) &rest
		)
	)
	
	(method (isNotHidden)
		(return 0)
	)
)
