;;; Sierra Script 1.0 - (do not remove this comment)
;KERNEL.SH
;Definitions of the kernel interface for the Script interpreter.

(define	kernel	$ffff)


;;;(extern
;;;	;Resource handling.
;;;	Load					kernel	00
;;;	UnLoad				kernel	01
;;;	ScriptID				kernel	02
;;;	DisposeScript		kernel	03
;;;
;;;	;Object management.
;;;	Clone					kernel	04
;;;	DisposeClone		kernel	05
;;;	IsObject				kernel	06
;;;	RespondsTo			kernel	07
;;;
;;;	;Pictures.
;;;	DrawPic				kernel	08
;;;	Show					kernel	09
;;;	PicNotValid			kernel	10
;;;
;;;	;Animated objects & views.
;;;	Animate				kernel	11
;;;	SetNowSeen			kernel	12
;;;	NumLoops				kernel	13 ; view
;;;	NumCels				kernel	14 ; view/loop
;;;	CelWide				kernel	15	; view/loop/cel
;;;	CelHigh				kernel	16	; view/loop/cel
;;;	DrawCel				kernel	17	; view/loop/cel/left/top/priority/palette
;;;	AddToPic				kernel	18
;;;
;;;	;Window/dialog/controls.
;;;	NewWindow			kernel	19
;;;	GetPort				kernel	20
;;;	SetPort				kernel	21
;;;;;	SetPortRect			kernel	21		;this is the same kernel call as SetPort
;;;	DisposeWindow		kernel	22
;;;	DrawControl			kernel	23
;;;	HiliteControl		kernel	24
;;;	EditControl			kernel	25
;;;
;;;	;Screen text.
;;;	TextSize				kernel	26
;;;	Display				kernel	27
;;;
;;;	;Events.
;;;	GetEvent				kernel	28
;;;	GlobalToLocal		kernel	29
;;;	LocalToGlobal		kernel	30
;;;	MapKeyToDir			kernel	31
;;;
;;;	;Menu bar & status line.
;;;	DrawMenuBar			kernel	32
;;;	MenuSelect			kernel	33
;;;	AddMenu				kernel	34
;;;	DrawStatus			kernel	35
;;;
;;;	;Parsing.
;;;	Parse					kernel	36
;;;	Said					kernel	37
;;;	SetSynonyms			kernel	38
;;;
;;;	;Mouse functions.
;;;	HaveMouse			kernel	39
;;;	SetCursor			kernel	40
;;;
;;;
;;;	;Save/restore/restart.
;;;	SaveGame				kernel	41
;;;	RestoreGame			kernel	42
;;;	RestartGame			kernel	43
;;;	GameIsRestarting	kernel	44
;;;
;;;	;Sounds.
;;;	DoSound				kernel	45
		(enum
			MasterVol
			SoundOn
			RestoreSound
			NumVoices
			NumDACs
			Suspend
			InitSound
			KillSound
			PlaySound
			StopSound
			PauseSound
			FadeSound
			HoldSound
			MuteSound
			SetVol
			SetPri
			SetLoop
			UpdateCues
			MidiSend
			SetReverb

			ChangeSndState  ;; This will be removed after KQ5 cd and
								 ;; Jones cd are shipped (DO NOT USE)
		)


;;;	;List handling.
;;;	NewList				kernel	46
;;;	DisposeList			kernel	47
;;;	NewNode				kernel	48
;;;	FirstNode			kernel	49
;;;	LastNode				kernel	50
;;;	EmptyList			kernel	51
;;;	NextNode				kernel	52
;;;	PrevNode				kernel	53
;;;	NodeValue			kernel	54
;;;	AddAfter				kernel	55
;;;	AddToFront			kernel	56
;;;	AddToEnd				kernel	57
;;;	FindKey				kernel	58
;;;	DeleteKey			kernel	59
;;;
;;;	;Mathematical functions.
;;;	Random				kernel	60
;;;	Abs					kernel	61
;;;	Sqrt					kernel	62
;;;	GetAngle				kernel	63
;;;	GetDistance			kernel	64
;;;
;;;	;Miscellaneous.
;;;	Wait					kernel	65
;;;	GetTime				kernel	66
		; pass NO argument for 60ths second "ticks" value
		(enum	1	; 0 is undefined in SysTime
			SYSTIME1		; returns HHHH|MMMM|MMSS|SSSS	(1 sec resoulution, 12 Hr)
			SYSTIME2		; returns HHHH|HMMM|MMMS|SSSS (2 sec resoulution 24 Hr)
			SYSDATE		; returns YYYY|YYYM|MMMD|DDDD	(date since 1980)
		)

;;;	;String handling.
;;;	StrEnd				kernel	67
;;;	StrCat				kernel	68
;;;	StrCmp				kernel	69
;;;	StrLen				kernel	70
;;;	StrCpy				kernel	71
;;;	Format				kernel	72
;;;	GetFarText			kernel	73
;;;	ReadNumber			kernel	74
;;;
;;;	;Actor motion support.
;;;	BaseSetter			kernel	75
;;;	DirLoop				kernel	76
;;;	CantBeHere			kernel	77
;;;	OnControl			kernel	78
;;;	InitBresen			kernel	79
;;;	DoBresen				kernel	80
;;;	DoAvoider			kernel	81
;;;	SetJump				kernel	82
;;;
;;;	;Debugging support.
;;;	SetDebug				kernel	83
;;;	InspectObj			kernel	84
;;;	ShowSends			kernel	85
;;;	ShowObjs				kernel	86
;;;	ShowFree				kernel	87
;;;	MemoryInfo			kernel	88
		(enum
			LargestPtr
			FreeHeap
			LargestHandle
			FreeHunk
			TotalHunk
		)
;;;	StackUsage			kernel	89
		(enum
			PStackSize
			PStackMax
			PStackCur
			MStackSize
			MStackMax
			MStackCur
		)
;;;	Profiler				kernel	90
		(enum
			PRO_OPEN
			PRO_CLOSE
			PRO_ON
			PRO_OFF
			PRO_CLEAR
			PRO_REPORT
			TRACE_ON
			TRACE_OFF
			TRACE_RPT
		)
;;;	GetMenu				kernel	91
;;;	SetMenu				kernel	92
;;;
;;;	GetSaveFiles		kernel	93
;;;	GetCWD				kernel	94
;;;	CheckFreeSpace		kernel	95
;;;	ValidPath			kernel	96
;;;
;;;	CoordPri				kernel	97
		(define PTopOfBand 1)
;;;	StrAt					kernel	98
;;;	DeviceInfo			kernel	99
		(enum
			GetDevice
			CurDevice
			SameDevice
			DevRemovable
			CloseDevice
			SaveDevice
			SaveDirMounted	
			MakeSaveDirName
			MakeSaveFileName
			)
;;;	GetSaveDir			kernel	100
;;;	CheckSaveGame		kernel	101
;;;
;;;	ShakeScreen			kernel	102
		; shakes [dir]
		(enum	1
			shakeSDown
			shakeSRight
			shakeSDiagonal
		)


;;;	FlushResources		kernel	103
;;;	
;;;	SinMult				kernel	104
;;;	CosMult				kernel	105
;;;	SinDiv				kernel	106
;;;	CosDiv				kernel	107
;;;
;;;	Graph					kernel	108
		(enum	1				; ARGS								RETURNS
			GLoadBits		; bitmap number
			GDetect			; none 								# of colors available
			GSetPalette		; Palette number
			GDrawLine  		; x1/y1/x2/y2 mapSet colors...
			GFillArea  		; x/y/ mapSet colors...
			GDrawBrush 		; x/y/ size randomSeed mapSet colors...
			GSaveBits		; top/left/bottom/right mapSet				saveID of area
			GRestoreBits	; saveID from SaveBits
			GEraseRect		; top/left/bottom/right (draws visual in background color)
			GPaintRect		; top/left/bottom/right (draws visual in foreground color)
			GFillRect  		; top/left/bottom/right mapSet colors...
			GShowBits		; top/left/bottom/right mapSet
			GReAnimate		; top/left/bottom/right
			GInitPri			; horizon/base, Rebuild priority tables
		)

;;;	Joystick				kernel	109
		(enum	12
			JoyRepeat
		)

;;;	ShiftScreen			kernel	110
;;;
;;;	Palette				kernel	111

		(enum	1
			PAL_MATCH		; don't steal entries in CLUT
			PAL_REPLACE		; steal your exact entry in CLUT
		)
		(enum	1
			PALLoad			; palette number and replace/match 
			PALSet			; first, last, & FLAGS to set
			PALReset			; first, last, & FLAGS to reset
			PALIntensity	; first, last, & intensity (0-100)
			PALMatch			; Red, Green, Blue (all 0-255), returns index
			PALCycle			; first, last, & iterations (negative numbers go in reverse)
			PALSave
			PALRestore
		)
		; defines for FLAGS passed to PALSet, PALReset
		(define	PAL_IN_USE		1)		; this entry is in use
		(define	PAL_NO_MATCH	2)		; never match this color when remapping
		(define	PAL_NO_PURGE	4)		; never overwrite this color when adding a palette
		(define	PAL_NO_REMAP	8)		; never remap this value to another color
		(define	PAL_MATCHED		16)	; in sys pal, shows someone is sharing it

;;;	MemorySegment		kernel	112
		(enum
			MS_SAVE_FROM
			MS_RESTORE_TO
		)
		(define	MS_STRING	0)	; no length specified - use string length

;;;	Intersections		kernel	113
		; no doco at this time

;;;	Memory				kernel	114
		; Function codes for Memory allocation operations  (Memory)
		(enum 1
			MNeedPtr 
			MNewPtr
			MDisposePtr
			MCopy
			MReadWord
			MWriteWord
		)
;;;	ListOps				kernel	115
		; Function codes for list manipulation operations  (ListOps)
		(enum 1
			LEachElementDo 
			LFirstTrue	  
			LAllTrue		  
		)
;;;	FileIO				kernel	116
		(enum
			fileOpen
			fileClose
			fileRead
			fileWrite
			fileUnlink
			fileFGets
			fileFPuts
			fileSeek
			fileFindFirst
			fileFindNext
			fileExists
		)
;;;	DoAudio				kernel	117
		(enum	1
			WPlay
			Play
			Stop
			Pause
			Resume
			Loc
			Rate
			Volume
			Lang
			Bias
		)
;;;	DoSync				kernel	118
		(enum	
			StartSync
			NextSync
			StopSync
		)
;;;	AvoidPath			kernel	119
;;;	Sort					kernel	120
;;;	ATan					kernel	121
;;;	Lock					kernel	122
;;;	StrSplit				kernel	123
;;;	GetMessage			kernel	124
;;;)

