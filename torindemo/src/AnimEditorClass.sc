;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64875)
(include sci.sh)
(use Main)
(use aeDisplayClass)
(use aeSoundPlayClass)
(use aePicShowClass)
(use aeMessageSeqClass)
(use aeCueClass)
(use aeActorVisClass)
(use aeAnimPlayerClass)
(use Plane)
(use String)
(use Print)
(use File)
(use Actor)
(use System)


(instance aeStatusBar of aeStatusBarClass
	(properties)
)

(instance aeDisplay of aeDisplayClass
	(properties)
)

(instance aePlayer of aeAnimPlayerClass
	(properties)
)

(instance aeNumNuts of View
	(properties)
)

(class AnimEditorClass of Obj
	(properties
		scratch 0
		canScrollDown 0
		remapColor 0
		normalizeRect 0
		remapPercent 0
		changeRemap 0
		bTileBorder 0
		nTopBorderHeight 0
		nScrollSpeedX 0
		unregisterEventHog 0
		nRightBorderWidth 0
		nScrollSpeedY 0
	)
	
	(method (init)
		(super init: &rest)
		(= remapColor (aeCastList new:))
		(theDoits add: self)
		(= nScrollSpeedX
			((Plane new:)
				back: 235
				priority: (+ 1 (GetHighPlanePri))
				init: 0 309 lastScreenX lastScreenY
				yourself:
			)
		)
		(= changeRemap aeStatusBar)
		(= nTopBorderHeight aeDisplay)
		(= bTileBorder aePlayer)
		(actor back: 223)
		(pic back: 219)
		(sfx back: 216)
		(msg back: 207)
		(ext back: 198)
		(nTopBorderHeight
			whoToCue: self
			fore: 234
			back: 235
			init: nScrollSpeedX
		)
		(changeRemap
			y: (- (nScrollSpeedX doDouble:) 20)
			width: (nScrollSpeedX findData:)
			height: 20
			init: nScrollSpeedX
		)
		(bTileBorder init: whoToCue: self)
		(theDoits add: self)
		(= nScrollSpeedY 0)
	)
	
	(method (doit)
		(if unregisterEventHog
			(self oMyPlane:)
			(if canScrollDown
				(if (not nRightBorderWidth) (nTopBorderHeight draw:))
				(changeRemap draw: (canScrollDown nCutoff?))
			)
		)
	)
	
	(method (dispose &tmp temp0 temp1 temp2)
		(if nScrollSpeedY
			(= temp1 5)
			(= temp2 5)
			(switch
				(= temp0
					(Print
						x: 20
						y: 20
						addTitle: {Save}
						addText: {File needs saving} temp1 temp2
						addButton: 1 {Save} temp1 (= temp2 (+ temp2 25))
						addButton: 0 {Quit} (= temp1 (+ temp1 100)) temp2
						addButton: -1 {Cancel} (= temp1 (+ temp1 100)) temp2
						init:
					)
				)
				(1 (self save:))
				(-1 (return))
			)
		)
		(MonoOut {Goodbye!})
		(= canScrollDown 0)
		(remapColor dispose:)
		(theDoits delete: self)
		(bTileBorder dispose:)
		(changeRemap dispose:)
		(nTopBorderHeight dispose:)
		(if normalizeRect
			(normalizeRect dispose:)
			(= normalizeRect 0)
		)
		(theDoits delete: self)
		(super dispose:)
	)
	
	(method (pause)
		(bTileBorder pause:)
	)
	
	(method (nWidth)
		(bTileBorder nWidth:)
	)
	
	(method (play)
		(bTileBorder play:)
	)
	
	(method (save)
		(if (self nOriginY: 2 &rest)
			(remapColor save: remapPercent)
			(remapPercent dispose:)
			(= nScrollSpeedY (= remapPercent 0))
		else
			(MonoOut {file open failed})
			(SetDebug)
		)
	)
	
	(method (doClick &tmp temp0 temp1 temp2 temp3)
		(= temp0 5)
		(= temp1 5)
		(= temp2 150)
		(= temp3 12)
		(Print
			x: 20
			y: 20
			width: 400
			fore: 235
			back: 234
			skip: 0
			font: userFont
			addTitle: {AnimationEditor Help}
			addText: {Commands:} temp0 (= temp1 5)
			addText: {ALT keys} temp0 (= temp1 (+ temp1 temp3))
			addText: {ALT-E__edit current object} temp0 (= temp1 (+ temp1 temp3))
			addText: {ALT-L__load} temp0 (= temp1 (+ temp1 temp3))
			addText: {ALT-S__load} temp0 (= temp1 (+ temp1 temp3))
			addText: {ALT-V__look at views} temp0 (= temp1 (+ temp1 temp3))
			addText: {ALT-X__quit} temp0 (= temp1 (+ temp1 temp3))
			addText: {_} (= temp0 (+ temp0 temp2)) (= temp1 5)
			addText: {Function keys} temp0 (= temp1 (+ temp1 temp3))
			addText: {F1___this help} temp0 (= temp1 (+ temp1 temp3))
			addText: {_} (= temp0 (+ temp0 temp2)) (= temp1 5)
			addText: {Normal keys} temp0 (= temp1 (+ temp1 temp3))
			addText: {h___item help} temp0 (= temp1 (+ temp1 temp3))
			addText: {p___play} temp0 (= temp1 (+ temp1 temp3))
			addText: {r___resume} temp0 (= temp1 (+ temp1 temp3))
			addText: {z___Zoom in (more detail)} temp0 (= temp1 (+ temp1 temp3))
			addText: {Z___Zoom out (less detail)} temp0 (= temp1 (+ temp1 temp3))
			addText: {mouse/key pause animation} temp0 (= temp1 (+ temp1 temp3))
			init:
		)
	)
	
	(method (load &tmp temp0)
		(if (self nOriginY: 1 &rest)
			(remapPercent dispose:)
			(= remapPercent 0)
			(= nRightBorderWidth 1)
			(bTileBorder
				dispose:
				whoToCue: self
				setScaleDirect: aeActorInterceptClass
				setScalePercent: aePicInterceptClass
				getMainCast: aeSoundInterceptClass
				whoToTurn: aeMessInterceptClass
				whatToFace: aeExternalInterceptClass
				init:
				load: normalizeRect
			)
			(= temp0 0)
			(bTileBorder face: (bTileBorder approachThenFace?))
			(while
			(< (bTileBorder face?) (bTileBorder initItems?))
				(bTileBorder nTilesX: temp0)
				(++ temp0)
			)
			(= nRightBorderWidth 0)
			(bTileBorder
				dispose:
				setScaleDirect: 0
				setScalePercent: 0
				getMainCast: 0
				whoToTurn: 0
				whatToFace: 0
				init:
				whoToCue: self
			)
			(nTopBorderHeight normalize: 0)
		else
			(MonoOut {file open failed})
		)
	)
	
	(method (add theCanScrollDown)
		(remapColor add: theCanScrollDown)
		(nTopBorderHeight add: theCanScrollDown)
		(= canScrollDown theCanScrollDown)
		(= unregisterEventHog 1)
	)
	
	(method (delete param1)
		(remapColor delete: param1)
		(if (not nRightBorderWidth)
			(nTopBorderHeight delete: param1)
		)
		(if (== canScrollDown param1) (= canScrollDown 0))
		(param1 dispose:)
		(= unregisterEventHog 1)
	)
	
	(method (canScrollUp param1 &tmp temp0 newActor temp2 temp3 temp4)
		(if
		(not (= newActor (remapColor setGroup: param1)))
			(= temp2 5)
			(= temp3 5)
			(= temp4 25)
			(switch
				(= temp0
					(Print
						x: 20
						y: 20
						addTitle: {Select Type}
						addButton: 1 {Actor} temp2 temp3
						addButton: 2 {Pic} temp2 (= temp3 (+ temp3 temp4))
						addButton: 3 {Sound} temp2 (= temp3 (+ temp3 temp4))
						addButton: 4 {Message} temp2 (= temp3 (+ temp3 temp4))
						addButton: 5 {External} temp2 (= temp3 (+ temp3 temp4))
						addButton: -1 {Cancel} temp2 (= temp3 (+ temp3 temp4))
						init:
					)
				)
				(1
					((= newActor (actor new:)) back: 223)
				)
				(2
					((= newActor (pic new:)) back: 219)
				)
				(3
					((= newActor (sfx new:)) back: 216)
				)
				(4
					((= newActor (msg new:)) back: 207)
				)
				(5
					((= newActor (ext new:)) back: 198)
				)
				(else  (return))
			)
			(if
			(newActor getBitmap: param1 whoToCue: self init: edit:)
				(self add: newActor)
				(self nLeading: newActor)
			else
				(newActor dispose:)
			)
		else
			(self nLeading: newActor)
		)
	)
	
	(method (nLeading theCanScrollDown)
		(if (!= canScrollDown theCanScrollDown)
			(= canScrollDown theCanScrollDown)
			(changeRemap draw: (canScrollDown nCutoff?))
		)
	)
	
	(method (nScrollDenomX &tmp newStr)
		(if canScrollDown
			(= newStr (Str new:))
			(newStr
				format: {Delete '%s'?} (canScrollDown nSpecialSelector?)
			)
			(if
				(==
					1
					(Print
						x: 20
						y: 20
						width: 200
						fore: 235
						back: 234
						skip: 0
						font: inputFont
						addTitle: {Delete}
						addText: newStr 5 5
						addButton: 1 {Yes} 5 30
						addButton: 0 {No} 55 30
						addButton: 0 {CANCEL} 105 30
						init:
					)
				)
				(self delete: canScrollDown)
			)
		)
	)
	
	(method (nScrollDenomY &tmp temp0 temp1 temp2 temp3 temp4 temp5 temp6 temp7 temp8)
		(= temp6 0)
		(= temp0 (Str with: {0}))
		(= temp1 (Str with: {0}))
		(= temp3 (+ (= temp2 5) 100))
		(= temp5 (+ 7 (Font 0 inputFont)))
		(repeat
			(= temp4 5)
			(Print
				x: 20
				y: 20
				width: 200
				fore: 235
				back: 234
				skip: 0
				font: inputFont
				addTitle: {View Displayer}
				addText: {View} temp2 (= temp4 (+ temp4 temp5))
				addEdit: temp0 5 temp3 temp4
				addText: {Loop} temp2 (= temp4 (+ temp4 temp5))
				addEdit: temp1 5 temp3 temp4
				addButton: 1 {Preview} temp2 (= temp4 (+ temp4 temp5))
				addButton: -1 {Quit} temp3 temp4
			)
			(if (< (Print init:) 0) (break))
			(aeNumNuts view: (temp0 asInteger:))
			(if (ResCheck 128 (aeNumNuts view?))
				(aeNumNuts loop: (temp1 asInteger:))
				(if (>= (aeNumNuts loop?) 0)
					(if
						(and
							(>= (aeNumNuts loop?) 0)
							(< (aeNumNuts loop?) (NumLoops aeNumNuts))
						)
						(if (> (NumCels aeNumNuts) 0)
							(aeNumNuts posn: 300 250 init:)
							(= temp7 0)
							(while (< temp7 (NumCels aeNumNuts))
								(aeNumNuts cel: temp7)
								(UpdateScreenItem aeNumNuts)
								(= temp8 150)
								(while temp8
									(FrameOut)
									(-- temp8)
								)
								(++ temp7)
							)
							(aeNumNuts dispose:)
						else
							(MonoOut {View %s, loop %s has no cels} temp0 temp1)
						)
					else
						(MonoOut {View %s, loop #%s too large} temp0 temp1)
						(MonoOut {Maximum of %d} (- (NumLoops aeNumNuts) 1))
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
	
	(method (handleEvent event &tmp temp0)
		(if
			(and
				(bTileBorder playing?)
				(& (event type?) evMOUSEKEYBOARD)
			)
			(self pause:)
		)
		(if (not (event claimed?))
			(nTopBorderHeight handleEvent: event)
		)
		(if canScrollDown (canScrollDown handleEvent: event))
		(if (not (event claimed?)) (self nOriginX: event))
		(event claimed?)
	)
	
	(method (nOriginX param1 &tmp [temp0 2])
		(switch (param1 type?)
			(4
				(switch (param1 message?)
					(9728
						(self load:)
						(param1 claimed: 1)
					)
					(7936
						(self save:)
						(param1 claimed: 1)
					)
					(4096
						(self dispose:)
						(param1 claimed: 1)
					)
					(12032
						(self nScrollDenomY:)
						(param1 claimed: 1)
					)
					(11520
						(self dispose:)
						(param1 claimed: 1)
					)
					(15104
						(self doClick:)
						(param1 claimed: 1)
					)
					(114
						(self nWidth:)
						(param1 claimed: 1)
					)
					(112
						(self play:)
						(param1 claimed: 1)
					)
					(122
						(nTopBorderHeight
							setScale: (+ (nTopBorderHeight setString?) 5)
						)
						(param1 claimed: 1)
					)
					(90
						(nTopBorderHeight
							setScale: (- (nTopBorderHeight setString?) 5)
						)
						(param1 claimed: 1)
					)
					(43
						(bTileBorder nBorderHeight:)
						(param1 claimed: 1)
					)
					(45
						(bTileBorder nBorderWidth:)
						(param1 claimed: 1)
					)
					(8
						(self nScrollDenomX:)
						(param1 claimed: 1)
					)
					(21248
						(self nScrollDenomX:)
						(param1 claimed: 1)
					)
					(else 
						(if
							(and
								(<= 48 (param1 message?))
								(<= (param1 message?) 57)
							)
							(self canScrollUp: (- (param1 message?) 48))
							(param1 claimed: 1)
						)
					)
				)
			)
		)
		(return 1)
	)
	
	(method (nOriginY param1 param2 &tmp temp0)
		(if (not argc)
			(MonoOut {AnimEditor openFile: no mode specified})
			(SetDebug)
		)
		(if (not normalizeRect)
			(= normalizeRect (Str with: {file.anm}))
		)
		(if (== argc 1)
			(if
			(not (= temp0 (GetInput normalizeRect 50 {Filename:})))
				(return)
			)
		else
			(normalizeRect copy: param2)
		)
		(if (not remapPercent) (= remapPercent (File new:)))
		(remapPercent init: name: (normalizeRect data?))
		(remapPercent open: param1)
	)
	
	(method (oMyPlane)
		(self save: {animedit.anm})
		(bTileBorder load: {animedit.anm} nTilesX: 0)
		(= unregisterEventHog 0)
		(= nScrollSpeedY 1)
	)
)
