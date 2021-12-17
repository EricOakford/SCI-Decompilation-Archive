;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64883)
(include sci.sh)
(use Main)
(use Cast)
(use String)
(use Array)
(use Print)
(use Sound)
(use File)
(use Actor)
(use System)


(class AnimPic of Obj
	(properties
		scratch 0
		just -1
		plane 0
	)
	
	(method (show)
		(plane drawPic: just)
	)
)

(class AnimExternal of Obj
	(properties
		scratch 0
		hotspotVerbList 0
		plane 0
	)
	
	(method (dispose)
		(if hotspotVerbList (hotspotVerbList dispose:))
	)
	
	(method (cue param1 &tmp animExternalForceCursor)
		(if (= animExternalForceCursor (self forceCursor:))
			(animExternalForceCursor cue: param1)
		)
	)
	
	(method (forceCursor)
		(plane forceCursor: hotspotVerbList)
	)
)

(class AnimMessage of Obj
	(properties
		scratch 0
		noun 0
		verb 0
		case 0
		addHotspotVerb 0
		deleteHotspotVerb 0
		testHotspotVerb 0
		getHotspotVerbList 0
		myPriority -1
	)
	
	(method (say theMyPriority)
		(if messager
			(messager
				cue: 1
				sayRange: noun verb case theMyPriority theMyPriority 0 testHotspotVerb
			)
		else
			(if (not getHotspotVerbList)
				(Prints {Game has no messager. Consider yourself warned})
				(= getHotspotVerbList 1)
			)
			(DoAudio
				2
				testHotspotVerb
				noun
				verb
				case
				theMyPriority
				1
				127
			)
		)
		(= myPriority theMyPriority)
	)
	
	(method (pause param1)
		(if (or (not argc) param1)
			(DoAudio 4 testHotspotVerb noun verb case myPriority)
		else
			(DoAudio 2 testHotspotVerb noun verb case myPriority)
		)
	)
)

(class AnimPlayer of Obj
	(properties
		scratch 0
		cuee 0
		normalizeRect 0
		oMyFeatures 0
		notifyDispose 0
		setScaleDirect 0
		setScalePercent 0
		getMainCast 0
		whoToTurn 0
		whatToFace 0
		whoToCue 0
		playing 0
		facer 0
		approach 0
		approachThenFace 0
		face 0
		normalize 0
		stopwalk 0
		getHeight 100
		getWidth 0
		findCastMember 0
		initItems 0
		isEnabled 0
		bBorder 0
		nSizeMode 0
	)
	
	(method (init)
		(if (not setScaleDirect) (= setScaleDirect View))
		(if (not setScalePercent) (= setScalePercent AnimPic))
		(if (not getMainCast) (= getMainCast Sound))
		(if (not whoToTurn) (= whoToTurn AnimMessage))
		(if (not whatToFace) (= whatToFace AnimExternal))
		(= getHeight 101)
		(if (not oMyFeatures) (= oMyFeatures (curRoom plane?)))
		((= notifyDispose (Cast new:)) plane: oMyFeatures)
	)
	
	(method (doit &tmp temp0)
		(if (> (= temp0 (- gameTime facer)) normalize)
			(self nTilesX: temp0)
		)
	)
	
	(method (dispose)
		(if playing (self stop:))
		(self nBaseTilesX:)
		(if nSizeMode (nSizeMode dispose:) (= nSizeMode 0))
		(if notifyDispose
			(notifyDispose release: dispose:)
			(= notifyDispose 0)
		)
		(if normalizeRect
			(normalizeRect dispose:)
			(= normalizeRect 0)
		)
		(super dispose:)
	)
	
	(method (load param1 &tmp newFile temp1 temp2)
		(if playing (self stop:))
		(= temp1 (Str with: param1))
		(self nTilesY: temp1)
		(if
			(and
				normalizeRect
				(== 0 (KString 7 normalizeRect temp1))
			)
			(return 1)
		)
		((= newFile (File new:)) init: name: (temp1 data?))
		(self nBaseTilesX:)
		(= temp2 0)
		(if (newFile open: 1)
			(if normalizeRect
				(normalizeRect copy: temp1)
			else
				(= normalizeRect (Str with: temp1))
			)
			(= initItems (FileIO fiSEEK (newFile handle?) 0 2))
			(FileIO fiSEEK (newFile handle?) 0 0)
			(= findCastMember (KString 0 initItems 3))
			(= temp2
				(FileIO
					fiREAD
					(newFile handle?)
					findCastMember
					initItems
				)
			)
			(= getWidth (self nOffsetY: 2))
			(self nBaseTilesY:)
			(self nHeight:)
		else
			(MonoOut {Could not open '%s'} (newFile name?))
		)
		(newFile dispose:)
		(return temp2)
	)
	
	(method (play)
		(= face approachThenFace)
		(= normalize 0)
		(= facer (= approach gameTime))
		(self nWidth:)
	)
	
	(method (stop)
		(if playing (= playing 0) (theDoits delete: self))
	)
	
	(method (hide &tmp temp0 temp1)
		(if (not isEnabled) (return))
		(= temp0 0)
		(while (< temp0 (isEnabled size:))
			(if
				(and
					(= temp1 (isEnabled at: temp0))
					(temp1 isKindOf: setScaleDirect)
				)
				(temp1 hide:)
			)
			(++ temp0)
		)
	)
	
	(method (pause)
		(= playing 0)
		(theDoits delete: self)
		(self nOffsetX: 1)
		(= approach gameTime)
	)
	
	(method (nWidth)
		(= playing 1)
		(= facer (+ facer (- gameTime approach)))
		(theDoits add: self)
		(self nOffsetX: 0)
	)
	
	(method (nHeight &tmp temp0 temp1)
		(if (not isEnabled) (return))
		(= temp0 0)
		(while (< temp0 (isEnabled size:))
			(cond 
				((not (= temp1 (isEnabled at: temp0))))
				((temp1 isKindOf: setScaleDirect) (Load rsVIEW (temp1 view?)))
				((temp1 isKindOf: setScalePercent) (if (!= -1 (temp1 just?)) (Load rsPIC (temp1 just?))))
				((temp1 isKindOf: getMainCast)
					(if (ResCheck 141 (temp1 number?))
						(Load rsAUDIO (temp1 number?))
					else
						(Load rsSOUND (temp1 number?))
					)
				)
			)
			(++ temp0)
		)
	)
	
	(method (nTilesX param1 &tmp temp0 temp1 theNormalize temp3 temp4 temp5)
		(if (not findCastMember) (return))
		(bBorder fill: 0 (bBorder size:) 0)
		(repeat
			(if (>= face initItems)
				(self stop:)
				(if cuee (cuee cue:))
				(return)
			)
			(= temp0 (self nOffsetY: face))
			(= temp1 (self nOffsetY: (+ face 2)))
			(if
			(> (= theNormalize (self nOffsetY: (+ face 4))) param1)
				(break)
			)
			(= normalize theNormalize)
			(switch temp0
				(2
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						((isEnabled at: temp3) hide:)
						(bBorder at: temp3 1)
					)
				)
				(3
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						((isEnabled at: temp3) show:)
						(bBorder at: temp3 1)
					)
				)
				(19
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						(if (= temp4 (self nOffsetY: (+ face 8)))
							((isEnabled at: temp3) show:)
						else
							((isEnabled at: temp3) hide:)
						)
						(bBorder at: temp3 1)
					)
				)
				(4
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						(= temp4 (self nOffsetY: (+ face 8)))
						((isEnabled at: temp3) setPri: temp4)
						(bBorder at: temp3 1)
					)
				)
				(15
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						(= temp4 (self nOffsetY: (+ face 8)))
						((isEnabled at: temp3) cel: temp4)
						(bBorder at: temp3 1)
					)
				)
				(6
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						(= temp4 (self nOffsetY: (+ face 8)))
						(= temp5 (self nOffsetY: (+ face 10)))
						((isEnabled at: temp3) posn: temp4 temp5)
						(bBorder at: temp3 1)
					)
				)
				(14
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						((isEnabled at: temp3) show:)
					)
				)
				(12
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						((isEnabled at: temp3) play:)
					)
				)
				(13
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						((isEnabled at: temp3) stop:)
					)
				)
				(16
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						((isEnabled at: temp3) cue:)
					)
				)
				(18
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						(= temp4 (self nOffsetY: (+ face 8)))
						((isEnabled at: temp3) say: temp4)
					)
				)
				(17
					(if
						(and
							(<
								(= temp3 (self nOffsetY: (+ face 6)))
								(isEnabled size:)
							)
							(isEnabled at: temp3)
						)
						((isEnabled at: temp3) dispose:)
						(isEnabled at: temp3 0)
					)
				)
				(else 
					(MonoOut {illegal cmd %d} temp0)
				)
			)
			(= face (+ face 4 temp1))
		)
		(= temp3 0)
		(while (< temp3 (bBorder size:))
			(if (bBorder at: temp3)
				(if ((isEnabled at: temp3) isNotHidden:)
					(UpdateScreenItem (isEnabled at: temp3))
				)
				(bBorder at: temp3 0)
			)
			(++ temp3)
		)
	)
	
	(method (nTilesY param1 &tmp temp0 newStr)
		(= newStr (Str new:))
		(if (== (= temp0 (param1 indexOf: 58)) -1)
			(if (not nSizeMode)
				(= nSizeMode (Str newWith: 100 {}))
				(GetConfig {animDir} nSizeMode)
			)
			(newStr format: {%s\\%s} nSizeMode param1)
		else
			(newStr copy: param1)
		)
		(if (!= (= temp0 (newStr indexOf: 46)) -1)
			(newStr at: temp0 0)
		)
		(newStr cat: {.anm})
		(param1 copy: newStr)
		(newStr dispose:)
	)
	
	(method (nBaseTilesX &tmp temp0 temp1)
		(if isEnabled
			(= temp0 0)
			(while (< temp0 (isEnabled size:))
				(if (= temp1 (isEnabled at: temp0)) (temp1 dispose:))
				(++ temp0)
			)
			(isEnabled dispose:)
			(= isEnabled 0)
		)
		(if notifyDispose (notifyDispose release:))
		(if bBorder (bBorder dispose:) (= bBorder 0))
		(if findCastMember
			(KString 4 findCastMember)
			(= findCastMember 0)
		)
		(= facer
			(= playing
				(= face
					(= normalize (= stopwalk (= approachThenFace 0)))
				)
			)
		)
	)
	
	(method (nBaseTilesY &tmp temp0 temp1 temp2 temp3 temp4)
		(asm
			_line_   772
			_file_   {filename}
			_line_   776
			_line_   777
			ldi      65535
			aTop     approachThenFace
			_line_   778
			pushi    #new
			pushi    0
			class    IDArray
			send     4
			aTop     isEnabled
			_line_   780
			ldi      4
			sat      temp0
			_line_   781
code_101b:
			lst      temp0
			pToa     initItems
			lt?     
			bnt      code_1357
			_line_   783
			pushi    3
			pushi    2
			pTos     findCastMember
			lst      temp0
			ldi      1
			add     
			push    
			callk    KString,  6
			push    
			ldi      8
			shl     
			push    
			pushi    3
			pushi    2
			pTos     findCastMember
			lst      temp0
			callk    KString,  6
			or      
			sat      temp1
			lst      temp0
			ldi      2
			add     
			sat      temp0
			_line_   784
			pushi    3
			pushi    2
			pTos     findCastMember
			push    
			ldi      1
			add     
			push    
			callk    KString,  6
			push    
			ldi      8
			shl     
			push    
			pushi    3
			pushi    2
			pTos     findCastMember
			lst      temp0
			callk    KString,  6
			or      
			sat      temp2
			lst      temp0
			ldi      2
			add     
			sat      temp0
			_line_   785
			pushi    3
			pushi    2
			pTos     findCastMember
			push    
			ldi      1
			add     
			push    
			callk    KString,  6
			push    
			ldi      8
			shl     
			push    
			pushi    3
			pushi    2
			pTos     findCastMember
			lst      temp0
			callk    KString,  6
			or      
			aTop     stopwalk
			_line_   787
			lst      temp1
			dup     
			_line_   788
			ldi      1
			eq?     
			bnt      code_111d
			_line_   792
			pushi    #new
			pushi    0
			pToa     setScaleDirect
			send     4
			sat      temp3
			_line_   794
			pToa     whoToCue
			bnt      code_10db
			pushi    #respondsTo
			pushi    1
			pushi    708
			lat      temp3
			send     6
			bnt      code_10db
			pushi    #whoToCue
			pushi    1
			pTos     whoToCue
			lat      temp3
			send     6
code_10db:
			_line_   795
			_line_   796
			pushi    14
			pushi    1
			pushi    #nOffsetY
			pushi    #x
			lst      temp0
			ldi      2
			add     
			push    
			self     6
			push    
			_line_   797
			pushi    15
			pushi    1
			pushi    729
			pushi    #x
			lst      temp0
			ldi      4
			add     
			push    
			self     6
			push    
			_line_   799
			pushi    142
			pushi    1
			pTos     notifyDispose
			_line_   800
			pushi    105
			pushi    0
			lat      temp3
			send     22
			jmp      code_133a
code_111d:
			dup     
			_line_   803
			ldi      10
			eq?     
			bnt      code_1183
			_line_   807
			pushi    #new
			pushi    0
			pToa     setScalePercent
			send     4
			sat      temp3
			_line_   808
			pToa     whoToCue
			bnt      code_1155
			pushi    #respondsTo
			pushi    1
			pushi    708
			lat      temp3
			send     6
			bnt      code_1155
			pushi    #whoToCue
			pushi    1
			pTos     whoToCue
			lat      temp3
			send     6
code_1155:
			_line_   809
			_line_   810
			pushi    692
			pushi    #x
			pushi    729
			pushi    #x
			lst      temp0
			ldi      2
			add     
			push    
			self     6
			push    
			_line_   811
			pushi    0
			pushi    1
			pTos     oMyFeatures
			_line_   812
			pushi    142
			pushi    0
			lat      temp3
			send     16
			jmp      code_133a
code_1183:
			dup     
			_line_   815
			ldi      11
			eq?     
			bnt      code_11f5
			_line_   819
			pushi    #new
			pushi    0
			pToa     getMainCast
			send     4
			sat      temp3
			_line_   820
			pToa     whoToCue
			bnt      code_11bb
			pushi    #respondsTo
			pushi    1
			pushi    708
			lat      temp3
			send     6
			bnt      code_11bb
			pushi    #whoToCue
			pushi    1
			pTos     whoToCue
			lat      temp3
			send     6
code_11bb:
			_line_   821
			_line_   822
			pushi    52
			pushi    1
			pushi    #nOffsetY
			pushi    #x
			lst      temp0
			ldi      2
			add     
			push    
			self     6
			push    
			_line_   823
			pushi    15
			pushi    1
			pushi    729
			pushi    #x
			lst      temp0
			ldi      4
			add     
			push    
			self     6
			push    
			_line_   824
			pushi    142
			pushi    0
			lat      temp3
			send     16
			jmp      code_133a
code_11f5:
			dup     
			_line_   827
			ldi      8
			eq?     
			bnt      code_12bd
			_line_   831
			pushi    #new
			pushi    0
			pToa     whoToTurn
			send     4
			sat      temp3
			_line_   832
			pToa     whoToCue
			bnt      code_122d
			pushi    #respondsTo
			pushi    1
			pushi    708
			lat      temp3
			send     6
			bnt      code_122d
			pushi    #whoToCue
			pushi    1
			pTos     whoToCue
			lat      temp3
			send     6
code_122d:
			_line_   833
			_line_   834
			pushi    291
			pushi    1
			pushi    729
			pushi    #x
			lst      temp0
			ldi      2
			add     
			push    
			self     6
			push    
			_line_   835
			pushi    292
			pushi    1
			pushi    #nOffsetY
			pushi    #x
			lst      temp0
			ldi      4
			add     
			push    
			self     6
			push    
			_line_   836
			pushi    293
			pushi    1
			pushi    729
			pushi    #x
			lst      temp0
			ldi      6
			add     
			push    
			self     6
			push    
			_line_   837
			pushi    695
			pushi    1
			pushi    729
			pushi    #x
			lst      temp0
			ldi      8
			add     
			push    
			self     6
			push    
			_line_   838
			pushi    696
			pushi    1
			pushi    729
			pushi    #x
			lst      temp0
			ldi      10
			add     
			push    
			self     6
			push    
			_line_   839
			pushi    697
			pushi    1
			pushi    729
			pushi    #x
			lst      temp0
			ldi      12
			add     
			push    
			self     6
			push    
			_line_   840
			pushi    142
			pushi    0
			lat      temp3
			send     40
			jmp      code_133a
code_12bd:
			dup     
			_line_   843
			ldi      9
			eq?     
			bnt      code_1328
			_line_   847
			pushi    730
			pushi    #x
			lst      temp0
			ldi      2
			add     
			push    
			self     6
			sat      temp4
			_line_   848
			pushi    #new
			pushi    0
			pToa     whatToFace
			send     4
			sat      temp3
			_line_   849
			pToa     whoToCue
			bnt      code_1307
			pushi    #respondsTo
			pushi    1
			pushi    708
			lat      temp3
			send     6
			bnt      code_1307
			pushi    #whoToCue
			pushi    1
			pTos     whoToCue
			lat      temp3
			send     6
code_1307:
			_line_   850
			_line_   851
			pushi    #hotspotVerbList
			pushi    1
			lst      temp4
			_line_   852
			pushi    0
			pushi    1
			pTos     oMyFeatures
			_line_   853
			pushi    142
			pushi    0
			lat      temp3
			send     16
			jmp      code_133a
code_1328:
			_line_   856
			_line_   858
			lst      temp0
			ldi      4
			sub     
			aTop     approachThenFace
			_line_   859
			jmp      code_1357
code_133a:
			toss    
			_line_   862
			pushi    #at
			pushi    2
			pTos     stopwalk
			lst      temp3
			pToa     isEnabled
			send     8
			_line_   863
			lst      temp0
			lat      temp2
			add     
			sat      temp0
			jmp      code_101b
code_1357:
			_line_   867
			pTos     approachThenFace
			ldi      65535
			eq?     
			bnt      code_1368
			_line_   868
			lat      temp0
			aTop     approachThenFace
code_1368:
			_line_   873
			pToa     approachThenFace
			aTop     face
			_line_   876
			pushi    #new
			pushi    0
			class    ByteArray
			send     4
			aTop     bBorder
			_line_   877
			pushi    #copy
			pushi    1
			pTos     isEnabled
			pToa     bBorder
			send     6
			_line_   878
			ret     
		)
	)
	
	(method (nOffsetX param1 &tmp temp0 temp1)
		(= temp0 0)
		(while (< temp0 (isEnabled size:))
			(if
				(and
					(= temp1 (isEnabled at: temp0))
					(or
						(temp1 isKindOf: getMainCast)
						(temp1 isKindOf: whoToTurn)
					)
				)
				(temp1 pause: param1)
			)
			(++ temp0)
		)
	)
	
	(method (nOffsetY param1)
		(return
			(|
				(<< (KString 2 findCastMember (+ param1 1)) $0008)
				(KString 2 findCastMember param1)
			)
		)
	)
	
	(method (nBaseWidth param1 &tmp temp0 temp1)
		(= temp1 (self nOffsetY: param1))
		(= temp0 (Str with: temp1))
		(KString
			6
			(temp0 data?)
			0
			findCastMember
			(+ param1 2)
			temp1
		)
		(return temp0)
	)
)
