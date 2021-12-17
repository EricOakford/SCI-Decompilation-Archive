;;; Sierra Script 1.0 - (do not remove this comment)
(script# 12)
(include game.sh)
(use Main)
(use Location)
(use VMDMovie)
(use SpeedTst)
(use DText)
(use String)
(use Print)
(use Sound)
(use Motion)
(use File)
(use Actor)
(use System)

(public
	Demo 0
)

(local
	newStr_2
	newStr_3
	newStr_4
	local3
	local4
	currentResStartTime
	local6
	local7 =  4
	local8
	currentResStartTime_2
	local10
)
(procedure (localproc_12d1 &tmp newStrSize temp1 newStr)
	(return
		(if (== local7 4)
			(return 0)
		else
			(= newStr (String new:))
			(newStr name: {tmpStr})
			(newStr format: &rest)
			(= newStrSize (newStr size:))
			(tmpDlg
				font: 0
				text: (newStr data?)
				fore: 200
				back: 0
				setPri: 500
				setSize: 700
				x: 20
				y: 350
				init: ((gPlane_2 casts?) at: 0)
			)
			(newStr dispose:)
		)
	)
)

(class Demo of Location
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
		heading 0
		picObj 0
		exitNE 0
		exitN 0
		exitNW 0
		currentIndex 0
		currentRes 0
	)
	
	(method (init &tmp temp0)
		(super init:)
		(= newStr_2 (String new:))
		(newStr_2 data: {\t ;\n""} name: {seperators})
		(= newStr_3 (String new:))
		(newStr_3 data: {\t\n;"} name: {seperators})
		(= local4 (String newWith: 8))
		(= temp0 (== (CD 0 1) -1))
		(= howFast (SpeedTest))
		(if (not (DEMO_dat open: 1))
			(Prints {Missing Data File})
			(theGame quitGame:)
		)
		(if temp0
			(= local7 5)
			(copyres_bat open: 2)
			(= newStr_4 (String new:))
			(copyres_bat writeString: {echo y|del sound\\*.*\n\0D\n})
			(copyres_bat writeString: {echo y|del vmd\\*.*\n\0D\n})
			(copyres_bat writeString: {echo y|del pic\\*.*\n\0D\n})
			(copyres_bat writeString: {echo y|del view\\*.*\n\0D\n})
			(copyres_bat
				writeString: {echo y|copy m:\\prog\\vmd\\4100?.vmd vmd\n\0D\n}
			)
		else
			(= local8 (Platform 1))
		)
		(= currentRes (MyResourceItem new:))
		(currentRes init:)
		(while (DEMO_dat read: currentRes)
			(if temp0
				(switch (currentRes resType?)
					(0
						(newStr_4
							format: {echo y|copy m:\\prog\\vmd\\%s.vmd vmd\n\0D\n} local4
						)
					)
					(1
						(newStr_4
							format: {echo y|copy m:\\prog\\pic\\%s.p56 pic\n\0D\n} local4
						)
					)
					(2
						(newStr_4
							format: {echo y|copy m:\\prog\\sound\\%s.wav sound\n\0D\n} local4
						)
					)
					(3
						(newStr_4
							format: {echo y|copy m:\\prog\\view\\%s.v56 view\n\0D\n} local4
						)
					)
				)
				(copyres_bat writeString: (newStr_4 data?))
			)
			(resourceList addToEnd: currentRes)
			(= currentRes (MyResourceItem new:))
			(currentRes init:)
		)
		(if temp0 (copyres_bat close:) (newStr_4 dispose:))
		(newStr_3 dispose:)
		(newStr_2 dispose:)
		(local4 dispose:)
		(DEMO_dat close:)
		(self setPicObj: currentCameraAngle)
		(theGame handsOn:)
		(= currentIndex 0)
		(self setScript: DemoScript)
	)
	
	(method (dispose)
		(resourceList dispose:)
	)
	
	(method (handleEvent event)
		(cond 
			((OneOf (event message?) 11520 11520 27) (theGame quitGame:))
			((== currentResStartTime_2 -1)
				(if (& (event type?) mouseDown)
					(if (== (currentRes resType?) 4)
						(DemoScript prevAudTime: 0)
					)
					(DemoScript lastTicks: gameTime)
					(DemoScript cue:)
					(event claimed: 1)
					(return)
				)
			)
			(else (super handleEvent: event))
		)
	)
	
	(method (getNextResource param1 &tmp theCurrentRes theCurrentIndex temp2)
		(if (>= currentIndex (resourceList size:))
			(= currentIndex 0)
		)
		(if
		(or (== (myWave prevSignal?) -1) (not param1))
			(= currentRes (resourceList at: currentIndex))
			(++ currentIndex)
			(if (< currentIndex (resourceList size:))
				(= currentResStartTime
					((= theCurrentRes (resourceList at: currentIndex))
						startTime?
					)
				)
			else
				(= currentIndex 0)
				(= currentResStartTime
					((= theCurrentRes (resourceList at: 0)) startTime?)
				)
			)
		else
			(= currentRes (resourceList at: currentIndex))
			(cond 
				((== (currentRes resType?) 2)
					(if (< currentIndex (resourceList size:))
						(= currentResStartTime
							((= theCurrentRes (resourceList at: (+ currentIndex 1)))
								startTime?
							)
						)
						(++ currentIndex)
					else
						(= currentIndex 0)
						(= currentResStartTime
							((= theCurrentRes (resourceList at: 0)) startTime?)
						)
					)
				)
				((> (currentRes startTime?) param1)
					(= currentResStartTime (currentRes startTime?))
					(return 0)
				)
				((== (currentRes startTime?) -1))
				(else
					(= theCurrentIndex (+ currentIndex 1))
					(while (< theCurrentIndex (resourceList size:))
						(= theCurrentRes (resourceList at: theCurrentIndex))
						(if
							(or
								(and
									(<= (currentRes startTime?) param1)
									(< param1 (theCurrentRes startTime?))
								)
								(== (theCurrentRes startTime?) -1)
							)
							(= currentResStartTime (theCurrentRes startTime?))
							(= currentResStartTime_2 (currentRes startTime?))
							(= currentIndex theCurrentIndex)
							(return currentRes)
						else
							(= currentRes theCurrentRes)
						)
						(++ theCurrentIndex)
					)
					(= currentIndex theCurrentIndex)
				)
			)
		)
		(= currentResStartTime_2 (currentRes startTime?))
		(return currentRes)
	)
)

(class MyResourceItem of Object
	(properties
		scratch 0
		resType 0
		resNumber 0
		resName 0
		seconds 0
		loop -1
		startCel -1
		endCel -1
		textDesc 0
		startTime -1
		pixelDouble 0
	)
	
	(method (init)
		(super init: &rest)
		(= textDesc (String newWith: 20))
	)
	
	(method (dispose)
		(if textDesc (textDesc dispose:))
		(if resName (resName dispose:))
		(super dispose:)
	)
)

(instance resourceList of List
	(properties)
)

(instance currentCameraAngle of CameraAngle
	(properties
		picture 60
		edgeN 0
		edgeS 0
		edgeE 0
		edgeW 0
	)
)

(instance copyres_bat of File
	(properties
		name "copyres.bat"
	)
)

(instance DEMO_dat of File
	(properties
		name "DEMO.dat"
	)
	
	(method (open &tmp temp0 temp1)
		(= temp0 (String new: 100))
		(temp0 format: {%s%s} (gGNewStr data?) {Demo.dat})
		(self name: (temp0 data?))
		(= temp1 (super open: &rest))
		(temp0 dispose:)
		(return temp1)
	)
	
	(method (readString param1 param2 &tmp temp0)
		(if (= temp0 (super readString: param1 param2))
			(param1 strip: 135 136)
		)
		(return temp0)
	)
	
	(method (read param1 &tmp temp0 temp1 newStr)
		(= temp1 (String newWith: 132))
		(= newStr (String new:))
		(while
			(and
				(self readString: temp1 132)
				(or
					(not (temp1 size:))
					((temp1 subStr: 0 1) compare: {;})
				)
			)
		)
		(if
			(and
				(temp1 size:)
				(not ((temp1 subStr: 0 1) compare: {;}))
				(temp1 getToken: newStr_2 newStr)
				(!= (newStr at: 0) 0)
			)
			(newStr upper:)
			(newStr strip: 135 136)
			(cond 
				((newStr compare: {VMD}) (param1 resType: 0))
				((newStr compare: {PIC}) (param1 resType: 1))
				((newStr compare: {WAV}) (param1 resType: 2))
				((newStr compare: {VIEW}) (param1 resType: 3))
				((newStr compare: {END}) (param1 resType: 4))
				(else (MonoOut {error reading type}) (return 0))
			)
			(temp1 getToken: newStr_2 newStr)
			(param1 resName: (String new:))
			((param1 resName?) format: {%s} newStr)
			(local4 format: {%s} newStr)
			(param1 resNumber: (newStr asInteger:))
			(switch (param1 resType?)
				(0
					(temp1 getToken: newStr_2 newStr)
					(param1 startTime: (* (newStr asInteger:) 6))
					(if
						(and
							(< howFast 500)
							(ResCheck 151 (+ (param1 resNumber?) 1000))
						)
						(param1 resNumber: (+ (param1 resNumber?) 1000))
						(param1 pixelDouble: 1)
					)
				)
				(1
					(temp1 getToken: newStr_2 newStr)
					(param1 startTime: (* (newStr asInteger:) 6))
				)
				(2
					(temp1 getToken: newStr_2 newStr)
					(newStr strip: 135 136)
					(param1 seconds: (newStr asInteger:))
					(param1 startTime: 0)
				)
				(3
					(Load RES_VIEW (param1 resNumber?))
					(temp1 getToken: newStr_2 newStr)
					(newStr strip: 135 136)
					(param1 loop: (newStr asInteger:))
					(temp1 getToken: newStr_2 newStr)
					(newStr strip: 135 136)
					(param1 startCel: (newStr asInteger:))
					(temp1 getToken: newStr_2 newStr)
					(newStr strip: 135 136)
					(param1 endCel: (newStr asInteger:))
					(temp1 getToken: newStr_2 newStr)
					(param1 startTime: (* (newStr asInteger:) 6))
				)
				(4
					(temp1 getToken: newStr_2 newStr)
					(if (< (newStr asInteger:) 0)
						(param1 startTime: -1)
					else
						(param1 startTime: (* (newStr asInteger:) 6))
					)
				)
			)
			(if (temp1 size:)
				(temp1 strip: 135 136)
				(if ((temp1 subStr: 0 1) compare: {;})
					((param1 textDesc?) fill: 0 19 32)
				else
					(temp1 getToken: newStr_3 (param1 textDesc?))
					((param1 textDesc?) strip: 135 136)
				)
			else
				((param1 textDesc?) fill: 0 19 32)
			)
			(= temp0 1)
		else
			(= temp0 0)
		)
		(newStr dispose:)
		(temp1 dispose:)
		(return temp0)
	)
)

(instance myVMDMovie of VMDMovie
	(properties
		endPic 60
	)
)

(instance myView of Prop
	(properties
		x 296
		y 146
	)
)

(instance myWave of Sound
	(properties
		flags $0001
	)
	
	(method (init)
		(= local6 1)
		(super init: &rest)
	)
	
	(method (dispose)
		(= local6 0)
		(super dispose: &rest)
	)
)

(class DemoScript of Script
	(properties
		scratch 0
		client 0
		state $ffff
		start 0
		timer 0
		cycles 0
		seconds 0
		lastSeconds 0
		ticks 0
		lastTicks 0
		register 0
		script 0
		caller 0
		next 0
		audioTime 0
		prevAudTime 0
		prevResType 4
		currentRes 0
	)
	
	(method (init)
		(super init: &rest)
	)
	
	(method (doit)
		(self updateTime:)
		(if (== currentResStartTime_2 -1)
			(localproc_12d1 {Waiting %d} (/ audioTime 6))
		else
			(if (<= currentResStartTime audioTime) (self cue:))
			(if currentRes
				(localproc_12d1
					{Name: %s Start Time: %d CurrentTime: %d next start: %d SR: %d CD: %d Mem %d Err %d}
					(currentRes resName?)
					(/ (currentRes startTime?) 6)
					(/ audioTime 6)
					(/ currentResStartTime 6)
					howFast
					local8
					(MemoryInfo 0)
					local10
				)
			else
				(localproc_12d1
					{Name: NULL Start Time: %d CurrentTime: ** next start: %d SR: %d CD: %d Mem %d Err %d}
					(/ audioTime 6)
					(/ currentResStartTime 6)
					howFast
					local8
					(MemoryInfo 0)
					local10
				)
			)
		)
	)
	
	(method (dispose)
		(super dispose: &rest)
	)
	
	(method (changeState newState &tmp newStr)
		(switch (= state newState)
			(0
				(gRamaInterface
					displayOptionsText: ((currentRes textDesc?) data?)
				)
				(switch (currentRes resType?)
					(0
						(= newStr (String new:))
						(newStr
							format: {%d.vmd} (currentRes resNumber?)
							name: {vmdName}
						)
						(myVMDMovie movieName: (newStr data?))
						(= state -1)
						(if (currentRes pixelDouble?)
							(myVMDMovie options: 1)
						else
							(myVMDMovie options: 0)
						)
						(theCursor setTempCursor: ramanWaitCursor)
						(myVMDMovie play:)
						(newStr dispose:)
					)
					(1
						(curRoom drawPic: 60)
						(FrameOut)
						(= state -1)
						(curRoom drawPic: (currentRes resNumber?))
					)
					(3
						(curRoom drawPic: 60)
						(FrameOut)
						(myView view: (currentRes resNumber?))
						(myView loop: (currentRes loop?))
						(myView cel: (currentRes startCel?))
						(myView init: gRoomCast)
						(myView setCycle: CycleTo (currentRes endCel?) 1 self)
					)
					(2
						(if local6 (myWave stop:))
						(= local6 1)
						(= state -1)
						(DoAudio 12 0)
						(myWave number: (currentRes resNumber?))
						(myWave play: 127 0 setLoop: (currentRes seconds?))
						(myWave prevSignal: 0)
					)
					(4
						(= local10 (= prevAudTime 0))
					)
				)
			)
			(1
				(= ticks (= seconds (= cycles 0)))
				(= state -1)
				(switch prevResType
					(3
						(myView dispose:)
						(self changeState: 0)
					)
					(4
						(if
						(and local6 (not (== (myWave prevSignal?) -1)))
							(myWave stop:)
						)
						(curRoom drawPic: 60)
						(myWave prevSignal: 0)
						(FrameOut)
						(self changeState: 0)
					)
				)
			)
		)
	)
	
	(method (cue &tmp theCurrentRes)
		(self updateTime:)
		(if currentRes (= prevResType (currentRes resType?)))
		(if
		(= theCurrentRes (Demo getNextResource: audioTime))
			(= currentRes theCurrentRes)
			(localproc_12d1
				{Name: %s Start Time: %d CurrentTime: %d next start: %d SR: %d CD: %d Mem %d Err %d}
				(currentRes resName?)
				(/ (currentRes startTime?) 6)
				(/ audioTime 6)
				(/ currentResStartTime 6)
				howFast
				local8
				(MemoryInfo 0)
				local10
			)
			(self changeState: (+ state 1) &rest)
		)
	)
	
	(method (updateTime &tmp temp0)
		(= temp0 (Abs (- gameTime lastTicks)))
		(= lastTicks gameTime)
		(= audioTime (DoAudio 6))
		(if (and (<= audioTime 0) prevAudTime)
			(= local10 1)
			(= audioTime (+ prevAudTime temp0))
		)
		(= prevAudTime audioTime)
	)
)

(instance tmpDlg of DText
	(properties)
)

(instance ramanWaitCursor of View
	(properties
		view 997
	)
)
