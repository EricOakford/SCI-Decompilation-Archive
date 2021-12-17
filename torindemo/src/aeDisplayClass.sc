;;; Sierra Script 1.0 - (do not remove this comment)
(script# 64876)
(include sci.sh)
(use Main)
(use Intrface)
(use Event)
(use String)
(use Array)
(use Actor)
(use System)


(class aeDisplayClass of Obj
	(properties
		scratch 0
		fore 234
		back 235
		skip 254
		font 0
		createBitmap 100
		setString 0
		whoToCue 0
		plane 0
		bitmap 0
		view 0
		width -1
		height -1
		vTile -1
		nMaxChars -1
		oDEdit -1
		nOffX -1
		nOffY -1
		rePosn -1
		module -1
		bDefault 50
		bCancel 0
		registerHandsOffActive 0
		registerKeyHandler 0
		normalize 0
		unregisterKeyHandler 1
		unregisterHandsOffActive 1
		nItems 1
	)
	
	(method (init thePlane_2 &tmp temp0)
		(= width ((= plane thePlane_2) findData:))
		(= height (thePlane_2 doDouble:))
		(= vTile (- width 1))
		(= nMaxChars (- height 1))
		(= bitmap
			(Bitmap 0 width height skip back screenWidth screenHeight)
		)
		(= view
			((View new:)
				bitmap: bitmap
				nsLeft: 0
				nsRight: width
				nsTop: 0
				nsBottom: height
				init: plane
				yourself:
			)
		)
		(= bCancel (Font 0 font))
		(= module (+ bCancel 3))
		(= oDEdit (/ height module))
		(= nOffX (/ (- width createBitmap) bDefault))
		(= nOffY 1)
		(= rePosn 0)
		(= registerHandsOffActive (IDArray new:))
		(= registerKeyHandler
			(AddLine plane 0 0 0 nMaxChars 1000 fore 0 0 2)
		)
		(self setScale: setString)
	)
	
	(method (dispose)
		(DeleteLine registerKeyHandler plane)
		(view dispose:)
		(registerHandsOffActive dispose:)
		(super dispose:)
	)
	
	(method (add param1 &tmp temp0)
		(if
		(< (= temp0 (param1 getBitmap?)) (- oDEdit 1))
			(registerHandsOffActive at: temp0 param1)
			(self draw:)
		else
			(MonoOut {No more room to display objects})
		)
	)
	
	(method (delete param1 &tmp temp0)
		(if
			(>=
				(= temp0 (registerHandsOffActive indexOf: param1))
				0
			)
			(registerHandsOffActive at: temp0 0)
			(self draw:)
		else
			(MonoOut {Object %s is not displayed} (param1 name?))
		)
	)
	
	(method (clear)
		(Bitmap 5 bitmap 0 0 vTile nMaxChars back)
		(UpdateScreenItem view)
	)
	
	(method (draw &tmp temp0 temp1 temp2 temp3 temp4)
		(self clear:)
		(self nDoubleMethod: normalize)
		(self oDataArray:)
		(= temp1 1)
		(= temp2 (+ module 1))
		(= temp0 0)
		(while (< temp0 (registerHandsOffActive size:))
			(if (= temp3 (registerHandsOffActive at: temp0))
				(temp3 bImAHog: (+ temp0 1))
			)
			(++ temp0)
		)
		(self nBottomBorderHeight:)
	)
	
	(method (nBottomBorderHeight theNormalize &tmp temp0)
		(if argc
			(if (self nDoubleMethod: theNormalize) (self draw:))
			(= normalize theNormalize)
		)
		(= temp0 (self getSelectNum: normalize))
		(UpdateLine
			registerKeyHandler
			plane
			temp0
			0
			temp0
			nMaxChars
		)
		(UpdateScreenItem view)
		(self oMyGroup: 0 0 back {Tick:%d} normalize)
	)
	
	(method (oDataArray &tmp theNOffY theCreateBitmap theModule theRePosn)
		(= theRePosn rePosn)
		(= theNOffY nOffY)
		(while (< theNOffY (+ nOffY nOffX))
			(self oMyGroup: theNOffY 0 back {%d} theRePosn)
			(= theRePosn (+ theRePosn nItems))
			(++ theNOffY)
		)
		(= theModule module)
		(= theNOffY 0)
		(while (< theNOffY oDEdit)
			(Bitmap
				2
				bitmap
				0
				theModule
				vTile
				theModule
				fore
				2
				4112
				1
			)
			(= theModule (+ theModule module))
			(++ theNOffY)
		)
		(= theCreateBitmap createBitmap)
		(= theNOffY 0)
		(while (<= theNOffY nOffX)
			(Bitmap
				2
				bitmap
				theCreateBitmap
				0
				theCreateBitmap
				nMaxChars
				fore
				1
				0
				1
			)
			(= theCreateBitmap (+ theCreateBitmap bDefault))
			(++ theNOffY)
		)
		(UpdateScreenItem view)
	)
	
	(method (oMyGroup param1 param2 param3 &tmp temp0 temp1 newStr theCreateBitmap)
		(if (== param1 0)
			(= temp0 1)
			(= theCreateBitmap createBitmap)
		else
			(= temp0 (self addItem: param1))
			(= theCreateBitmap (- bDefault 3))
		)
		(= temp1 (self find: param2))
		(= newStr (Str new:))
		(newStr format: &rest)
		(Bitmap
			4
			bitmap
			(newStr data?)
			temp0
			temp1
			(+ temp0 theCreateBitmap)
			(+ temp1 bCancel)
			fore
			param3
			skip
			font
			0
			-1
			0
		)
	)
	
	(method (isButtonDown param1 param2 param3 param4 &tmp temp0 temp1 temp2 temp3 theBack)
		(if (and (>= argc 4) param4)
			(= theBack back)
		else
			(= theBack fore)
		)
		(= temp1 (self getSelectNum: param2))
		(= temp2 (self getSelectNum: param3))
		(= temp3 (+ (* param1 module) (/ module 2)))
		(= temp1 (Max (Min temp1 vTile) createBitmap))
		(= temp2 (Min temp2 vTile))
		(self oDoubleNotify: temp1 temp3 theBack)
		(Bitmap 2 bitmap temp1 temp3 temp2 temp3 theBack 0 0 2)
		(self oDoubleNotify: temp2 temp3 theBack)
		(UpdateScreenItem view)
	)
	
	(method (oDoubleNotify param1 param2 param3 &tmp temp0 temp1 temp2 temp3)
		(= temp0 (+ param1 2))
		(= temp2 (- param1 2))
		(= temp1 (- param2 2))
		(= temp3 (+ param2 2))
		(= temp0 (Min temp0 vTile))
		(Bitmap 2 bitmap temp2 temp1 temp0 temp1 param3 0 0 2)
		(Bitmap 2 bitmap temp0 temp1 temp0 temp3 param3 0 0 2)
		(Bitmap 2 bitmap temp0 temp3 temp2 temp3 param3 0 0 2)
		(Bitmap 2 bitmap temp2 temp3 temp2 temp1 param3 0 0 2)
	)
	
	(method (setButton param1)
		(return (+ 1 (registerHandsOffActive indexOf: param1)))
	)
	
	(method (nDoubleMethod param1 &tmp temp0 temp1 temp2)
		(if (== param1 -1) (return (== param1 -1)))
		(= temp0 (- nOffY 1))
		(cond 
			((< param1 rePosn)
				(= nOffY (Max 1 (- temp0 (/ nOffX 2))))
				(= rePosn (* temp0 nItems))
				(return 1)
			)
			((> param1 (* (+ temp0 nOffX) nItems))
				(= temp1 (self getSelectNum: param1 0))
				(= nOffY (- (= temp2 (self setData: temp1)) 1))
				(= rePosn (* (- nOffY 1) nItems))
				(return 1)
			)
		)
		(return 0)
	)
	
	(method (onMe theObjOrX)
		(plane onMe: theObjOrX)
	)
	
	(method (nDoubleValue param1 &tmp temp0)
		(= temp0 (self getSelectNum: normalize))
		(return (< (Abs (- temp0 (param1 x?))) 3))
	)
	
	(method (registerEventHog param1 param2 &tmp temp0 temp1)
		(= temp0 (self setButton: param2))
		(return (== temp0 (= temp1 (self getData: (param1 y?)))))
	)
	
	(method (handleEvent event &tmp temp0 temp1 temp2)
		(if (not (self onMe: event)) (return))
		(switch (event type?)
			(evMOUSEBUTTON
				((= temp1 (Clone event)) localize: plane)
				(switch (event modifiers?)
					(emALT
						(if (self nDoubleValue: temp1)
							(self nClickTime:)
							(event claimed: 1)
						)
					)
					(0
						(if
							(and
								(== 0 (self setData: (temp1 x?)))
								(>=
									(= temp2 (- (= temp2 (self getData: (temp1 y?))) 1))
									0
								)
								(< temp2 (registerHandsOffActive size:))
								(registerHandsOffActive at: temp2)
								whoToCue
							)
							(whoToCue canScrollUp: temp2)
							(event claimed: 1)
						)
					)
				)
				(temp1 dispose:)
			)
		)
	)
	
	(method (nClickTime &tmp newEvent temp1)
		(repeat
			((= newEvent (Event new:)) localize: plane)
			(= temp1 (self bButtonDown: (newEvent x?)))
			(newEvent dispose:)
			(self nBottomBorderHeight: temp1)
			((whoToCue bTileBorder?) nLeftBorderWidth: temp1)
			(FrameOut)
			(breakif (not (StillDown)))
		)
		((whoToCue bTileBorder?) nLeftBorderWidth: temp1)
	)
	
	(method (setScale theSetString)
		(cond 
			((== (= setString theSetString) 0) (= unregisterHandsOffActive (= unregisterKeyHandler 1)))
			((< theSetString 0)
				(= unregisterKeyHandler 1)
				(= unregisterHandsOffActive (- 0 theSetString))
			)
			((> theSetString 0)
				(= unregisterKeyHandler theSetString)
				(= unregisterHandsOffActive 1)
			)
		)
		(= nItems
			(MulDiv
				unregisterKeyHandler
				bDefault
				unregisterHandsOffActive
			)
		)
		(self draw:)
	)
	
	(method (bButtonDown param1 &tmp temp0 temp1)
		(if (> param1 createBitmap)
			(= temp1 (- param1 createBitmap))
			(= temp0
				(+
					(= temp0
						(MulDiv
							temp1
							unregisterKeyHandler
							unregisterHandsOffActive
						)
					)
					rePosn
				)
			)
		else
			(= temp0 0)
		)
		(return temp0)
	)
	
	(method (getSelectNum param1 param2 &tmp temp0 temp1)
		(= temp1 (- param1 rePosn))
		(= temp0
			(+
				(= temp0
					(MulDiv
						temp1
						unregisterHandsOffActive
						unregisterKeyHandler
					)
				)
				createBitmap
			)
		)
		(if (or (== argc 1) param2)
			(= temp0 (Max createBitmap temp0))
			(= temp0 (Min vTile temp0))
		)
		(return temp0)
	)
	
	(method (getData param1)
		(Min (- oDEdit 1) (/ param1 module))
	)
	
	(method (setData param1 &tmp temp0 temp1)
		(if (<= param1 createBitmap)
			(= temp0 0)
		else
			(= temp0
				(+
					(= temp0
						(/ (= temp1 (- param1 createBitmap)) bDefault)
					)
					nOffY
				)
			)
		)
		(return temp0)
	)
	
	(method (addItem param1 &tmp temp0 temp1)
		(if (<= param1 0)
			(= temp1 2)
		else
			(= temp1
				(+
					(= temp1 (* (= temp0 (- param1 nOffY)) bDefault))
					2
					createBitmap
				)
			)
		)
		(return temp1)
	)
	
	(method (find param1)
		(Min nMaxChars (+ (* param1 module) 1))
	)
)
