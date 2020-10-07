;;; Sierra Script 1.0 - (do not remove this comment)
(script# TALKER)
(include game.sh)
(use Main)
(use Procs)
(use Intrface)
(use Sync)
(use Osc)
(use RandCyc)
(use Actor)
(use System)


(class RTRandCycle of RandCycle

	(method (doit &tmp theTime)
		(if
			(u>
				(- (= theTime (GetTime)) cycleCnt)
				(client cycleSpeed?)
			)
			(client cel: (self nextCel:))
			(= cycleCnt (GetTime))
		)
	)
	
	(method (init obj theTime whoCares)
		(super init: obj)
		(client cel:	0)
		(= cycleCnt (GetTime))
		(if (>= argc 2)			
			(= count (+ (GetTime) theTime)) 
			(if (>= argc 3)		
				(= caller whoCares)
			)
		else 
			(= count -1)
		)
	)

)

(class eyeOsc of Oscillate
	
	(method (init)
		(super init: &rest)
		(client cycleSpeed: (Random 100 200))
		(= cycleCnt (GetTime))
	)
	
	(method (nextCel &tmp theTime)
		(return
			(cond 
				(
					(<=
						(- (= theTime (GetTime)) cycleCnt)
						(client cycleSpeed?)
					)
					(return (client cel?))
				)
				((and (== (client cel?) 1) (< cycleDir 0))
					(client cycleSpeed: (Random 150 400))
					(return 0)
				)
				((not (client cel?))
					(client cycleSpeed: 6)
					(return (= cycleDir 1))
				)
				(else
					(= cycleCnt (GetTime))
					(return (+ (client cel?) cycleDir))
				)
			)
		)
	)
)

(class Talker kindof Prop
	(properties
		bust	NULL
		eyes	NULL
		mouth	NULL
		ticks	NULL
		disposeWhenDone TRUE
		caller	NULL
	)

;;;	(methods 
;;;		init 		  		;initialize, add to fastCast, show, and optionally say
;;;		dispose			;delete from fastCast, and optionally cue caller and hide 
;;;		say 				;startText or startAudio based on cDAudio	global
;;;		startAudio 		;start talker sync'ing to audio
;;;		startText 		;print message and move mouth and eyes
;;;		cycle				;cycle mouth and eyes and redraw them
;;;		show				;draw all components of talker on screen
;;;		hide				;erase talker from screen
;;;		doit				;a fastCast animation cycle
;;;		handleEvent		;dispose if mouseDown or ENTER
;;;	)
	
	(method (init bustView prop syncedProp modNum charNum msgNum dWD whoCares
			&tmp pnv  
			)
		(if argc
			(= bust bustView)
			(if (>= argc 2)
				(= eyes prop)
				(if (>= argc 3)
					(= mouth syncedProp)
					(if (>= argc 7)
						(= disposeWhenDone dWD)
						(if (>= argc 8)
							(= caller whoCares)
						)
					)
				)
			)
		)
		(self show:)
		(if (>= argc 4)
			(self	say: modNum charNum msgNum disposeWhenDone caller)
		)
	)
	
	(method (show &tmp pnv savePort)
		(= savePort (GetPort))
		(SetPort 0)
		(= nsRight
			(+
				nsLeft
				(Max
					(CelWide view loop cel)
					(if (IsObject bust)
						(+
							(bust nsLeft?)
							(CelWide (bust view?) (bust loop?) (bust cel?))
						)
					else
						0
					)
					(if (IsObject eyes)
						(+
							(eyes nsLeft?)
							(CelWide (eyes view?) (eyes loop?) (eyes cel?))
						)
					else
						0
					)
					(if (IsObject mouth)
						(+
							(mouth nsLeft?)
							(CelWide (mouth view?) (mouth loop?) (mouth cel?))
						)
					else
						0
					)
				)
			)
		)
		(= nsBottom
			(+
				nsTop
				(Max
					(CelHigh view loop cel)
					(if (IsObject bust)
						(+
							(bust nsTop?)
							(CelHigh (bust view?) (bust loop?) (bust cel?))
						)
					else
						0
					)
					(if (IsObject eyes)
						(+
							(eyes nsTop?)
							(CelHigh (eyes view?) (eyes loop?) (eyes cel?))
						)
					else
						0
					)
					(if (IsObject mouth)
						(+
							(mouth nsTop?)
							(CelHigh (mouth view?) (mouth loop?) (mouth cel?))
						)
					else
						0
					)
				)
			)
		)
		(if (not underBits)
			(= underBits
				(Graph GSaveBits nsTop nsLeft nsBottom nsRight 3)
			)
		)
		(Graph GFillRect nsTop nsLeft nsBottom nsRight 2 -1 15)
		(= pnv (PicNotValid))
		(PicNotValid TRUE)
		(DrawCel view loop cel nsLeft nsTop -1)
		(if bust
			(DrawCel
				(bust view?)
				(bust loop?)
				(bust cel?)
				(+ (bust nsLeft?) nsLeft)
				(+ (bust nsTop?) nsTop)
				-1
			)
		)
		(if eyes
			(DrawCel
				(eyes view?)
				(eyes loop?)
				0
				(+ (eyes nsLeft?) nsLeft)
				(+ (eyes nsTop?) nsTop)
				-1
			)
		)
		(if mouth
			(DrawCel
				(mouth view?)
				(mouth loop?)
				(mouth cel?)
				(+ (mouth nsLeft?) nsLeft)
				(+ (mouth nsTop?) nsTop)
				-1
			)
		)
		(Graph GShowBits nsTop nsLeft nsBottom nsRight 1)
		(PicNotValid pnv)
		(SetPort savePort)
	)

	(method (say modNum charNum msgNum dWD whoCares)
		(if (>= argc 4)
			(= disposeWhenDone dWD)
			(if (>= argc 5) (= caller whoCares))
		)
		(if cDAudio
			(self startAudio: modNum charNum msgNum)
		else
			(self startText: modNum charNum msgNum)
		)
		(if fastCast
			(fastCast add: self)
		else
			(= fastCast (EventHandler new:))
			(fastCast name: {fastCast} add: self)
		)
		(= ticks (+ ticks 60 (GetTime)))
	)

	(method (startText modNum charNum &tmp temp0 savePort [buffer 500] saveWinColor saveWinBack)
		;; Draw Talker face and text and leave it for a calculated
		;; amount of time
		;; TEST HOOK
		
		(= savePort (GetPort))
		(SetPort 0)
		(if modelessDialog
			(modelessDialog dispose:)
		)
		(Format @buffer modNum charNum)
		(= ticks (* 5 (StrLen @buffer)))
		(if mouth (mouth setCycle: RTRandCycle ticks))
		(if eyes (eyes setCycle: eyeOsc))
		(Measure userFont @buffer)
		(= saveWinColor (systemWindow color?))
		(= saveWinBack (systemWindow back?))
		(systemWindow back: 34 color: 37)
		(if (> global228 16)
			(Print @buffer #at x y #width 240 #dispose)
		else
			(Print @buffer #at x y #dispose)
		)
		(systemWindow
			back: saveWinBack
			color: saveWinColor
		)
		(SetPort savePort)
	)	
	
	(method (startAudio modNum &tmp theAudio)
		(= theAudio modNum)
		(DoAudio WPlay theAudio)
		(if mouth (mouth setCycle: MouthSync theAudio))
		(= ticks (DoAudio Play theAudio))
	)
	
	(method (cycle obj &tmp theCel savePort)
		;; call cycler doit and redraw obj if it has changed
		(= savePort (GetPort))
		(SetPort 0)
		(if (and obj (obj cycler?))
			(= theCel 	(obj cel?))
			((obj cycler?)		doit:)
			(if (!= theCel (obj cel?))
				(DrawCel	
					(obj	view?)
					(obj	loop?)
					(obj	cel?)	
					(+ (obj nsLeft?) nsLeft)
					(+ (obj nsTop?) nsTop)
					-1
				)
				(obj 
					nsRight: 
						(+ obj nsLeft 
							(CelWide (obj view?) (obj loop?)  (obj cel?))
						)
				)
				(obj 
					nsBottom: 
						(+  obj nsTop
							(CelHigh (obj view?) (obj loop?)  (obj cel?))
						)
				)
		 		(Graph GShowBits 
					(+ (obj nsTop?) 		nsTop)
					(+ (obj nsLeft?) 	nsLeft)
					(+ (obj nsBottom?) 	nsTop)
					(+ (obj nsRight?) 	nsLeft)
					VMAP
				)
			)
		)
		(SetPort savePort)
	)


	(method (doit &tmp savePort)
		(if (> (GetTime) ticks)
			(self dispose: disposeWhenDone)
			(return)
		)
		(= savePort (GetPort))
		(SetPort 0)
		(if eyes (self cycle: eyes))
		(if mouth (self cycle: mouth))
		(SetPort savePort)
	)
	
	(method (handleEvent event)
		(if (super handleEvent:event)
			(return)
		)
		(if (or
				(& (event type?) (| userEvent joyDown mouseDown))
				(and
					(& (event type?) keyDown)
					(== (event message?) ENTER)
				)
			)
			(event claimed: TRUE)
			(self dispose: disposeWhenDone)
		)
	)
	
	(method (hide &tmp savePort)
		(= savePort (GetPort))
		(SetPort 0)
		(Graph GRestoreBits underBits)
		(= underBits 0)
		(Graph GReAnimate nsTop nsLeft nsBottom nsRight)
		(SetPort savePort)
	)	

	(method (dispose dWD &tmp toCue savePort)
		(if (== global149 self)
			(= global149 (= global148 0))
		)
		(= savePort (GetPort))
		(SetPort 0)
		(if (and (IsObject fastCast) (fastCast contains: self))
			(fastCast delete: self)
			(if (fastCast isEmpty:)
				(fastCast dispose:)
				(= fastCast 0)
			)
		)
		(if (or (not argc) dWD)
			
			;; this is here because MouthSync is a non-standard 
			;; Cycler and thus needs special attention.
			(if (and mouth (mouth cycler?))
				(if ((mouth cycler?) respondsTo: #cue)
					((mouth cycler?) cue:)
				)
				(mouth setCycle: 0)
			)
			(cond 
				(cDAudio
					(DoAudio Stop)
				)
				(modelessDialog
					(modelessDialog dispose:)
				)
			)
			(if eyes (eyes setCycle: 0))
			(self hide:)
		)
		(if caller
			;; I know this looks stupid, but It gets rid of an
			;; incidious little bug.			
			(= toCue caller)
			(= caller 0)
			(toCue cue:)
		)
		;; in lue of super dispose:		
		(DisposeClone self)
		(SetPort savePort)
	)	
)
