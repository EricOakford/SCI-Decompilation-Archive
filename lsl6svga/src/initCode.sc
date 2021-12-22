;;; Sierra Script 1.0 - (do not remove this comment)
(script# 71)
(include sci.sh)
(use Main)
(use fileScr)
(use LL6Ego)
(use SpeedTst)
(use Plane)
(use String)
(use Array)
(use Print)
(use Flags)
(use User)
(use System)

(public
	initCode 0
)

(instance initCode of Code
	(properties)
	
	(method (init &tmp temp0)
		(= global100 (ResCheck 130 911))
		(= temp0 0)
		(while (<= temp0 20)
			(= [global216 temp0] 0)
			(= [global237 temp0] 0)
			(++ temp0)
		)
		(= global101 1234)
		(= global171 1)
		(= global176 -1)
		(theGame setCursor: waitCursor)
		(SetFontRes 640 480)
		((= gameFlags (Flags new:))
			name: {gameFlags}
			setSize: 300
		)
		(Bset 105)
		(thePlane setRect: 0 15 319 154)
		(= global110 (== (Platform 4) 2))
		(= global105 (Robot 1))
		(= global136 200)
		((ScriptID 81) init:)
		(DisposeScript 81)
		(User alterEgo: (= ego ego) canControl: 0 canInput: 0)
		(= useSortedFeatures 1)
		((= theMusic (ScriptID 0 15)) owner: theGame init:)
		((= theMusic2 (ScriptID 0 16)) owner: theGame init:)
		(= gGEgoCycleSpeed_2 1)
		((= controlPlane (Plane new:))
			name: {controlPlane}
			back: 0
			init: 0 0 319 14
			priority: (+ (GetHighPlanePri) 1)
			addCast: (ScriptID 0 1)
		)
		((= talkerPlane (Plane new:))
			name: {talkerPlane}
			back: 0
			init: 0 155 319 199
			priority: (+ (GetHighPlanePri) 1)
			addCast: (ScriptID 0 13)
		)
		((ScriptID 92 3)
			setPri: 200
			init: (ScriptID 0 13)
			yourself:
		)
		((ScriptID 914 0) init:)
		(Print back: 7 fore: 0 font: 2306)
		(ego setSpeed: gGEgoCycleSpeed_2)
		(= numVoices (DoSound sndDISPOSE))
		((ScriptID 85) init:)
		(timers
			add:
				((ScriptID 85 1) client: (ScriptID 85 1) yourself:)
				((ScriptID 85 2) client: (ScriptID 85 2) yourself:)
				((ScriptID 85 4) client: (ScriptID 85 4) yourself:)
				((ScriptID 0 12) client: (ScriptID 0 12) yourself:)
		)
		(= global186 (ByteArray new: 200))
		(= global177 (Str new: 200))
		(= global190 (Str new: 200))
		(Bset 9)
		((ScriptID 88 0) init:)
		((ScriptID 0 12) setReal: theGame 0 (= global183 10))
		(theGame masterVolume: 12)
		(= global194 11)
		(= gGButtonBarCurIcon 0)
		(= howFast (/ (* (- 10 (SpeedTest)) 15) 10))
		(theGame
			detailLevel: (cond 
				((< howFast 3) 0)
				((< howFast 10) 2)
				(else 3)
			)
		)
		(= global193 (/ (* (theGame detailLevel:) 26) 10))
		(= global260 (MemoryInfo 0))
		(if (not global208)
			(= global208
				((ScriptID 0 20)
					nsLeft: 40
					nsTop: 157
					nsRight: 269
					nsBottom: 195
					init: (ScriptID 0 17) (ScriptID 0 18) (ScriptID 0 19)
					yourself:
				)
			)
		)
	)
)
